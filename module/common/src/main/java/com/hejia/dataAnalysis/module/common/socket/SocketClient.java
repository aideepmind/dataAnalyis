package com.hejia.dataAnalysis.module.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.utils.WeixinTemplateUtil;

/**
 * @Description: 客户端
 * @author: chenyongqiang
 * @Date: 2015年9月16日
 * @version: 1.0
 */
public class SocketClient {
	
	/**
	 * 对象池
	 */
	private GenericObjectPool sop = new GenericObjectPool();

	private Logger log = Logger.getLogger(SocketClient.class.getName());
	
	private int poolSize;
	
	private int poolSizeMark;

	private Queue<Map<String, Object>> failQueue = new LinkedList<Map<String, Object>>();//发送失败队列，重试三次
	
	public SocketClient() {
		super();
	}
	
	/**
	 * @Definition: 初始化socket池
	 * @author: chenyongqiang
	 * @Date: 2015年9月16日
	 * @param poolSize 池的大小
	 * @param maxActive 最大的活跃数
	 * @param minIdle 最小空闲数
	 * @param maxIdle 最大空闲数
	 * @param ip
	 * @param port
	 * @throws Exception
	 */
	public void init(int poolSize, int maxActive, int minIdle, int maxIdle, String ip, int port) throws Exception {
		this.poolSize = poolSize;
		this.sop.setMaxActive(maxActive);
		this.sop.setMinIdle(minIdle);
		this.sop.setMaxIdle(maxIdle);
		this.sop.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_BLOCK);
		SocketPoolableObjectFactory sf = new SocketPoolableObjectFactory();
		sf.setIp(ip);
		sf.setPort(port);
		this.sop.setFactory(sf);
		for (int i = 0; i < poolSize; i++) {
			try {
				this.sop.addObject();
				this.poolSizeMark++;
			} catch (Exception e) {
				log.debug("初始化socket连接失败");
				break;
			}
		}
	}

	/**
	 * @Definition: 从池中取得socket进行数据的发送
	 * @author: chenyongqiang
	 * @Date: 2015年10月15日
	 * @param request
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public boolean send(SocketRequest request, String encoding) throws Exception {
		Socket s = null;
		try {
			s = (Socket) this.sop.borrowObject();
		} catch (Exception e) {
			log.debug("从socket池中获取连接失败");
		} finally {
			// 不依赖服务端与客户端启动先后顺序，如果从池中取得连接为空或者池中连接数小于要设置的池大小，向池中新增一个连接
			if (s == null || this.poolSizeMark + 1 < this.poolSize) {
				try {
					this.sop.addObject();
					this.poolSizeMark++;
				} catch (Exception e) {
					log.debug("取得socket连接失败");
				}
				if (s == null) s = (Socket) this.sop.borrowObject();
			}
		}
		try {
			boolean flag = sendInner(request, s , encoding);
			if (!flag) {
				boolean flag1 = false;
				for (int i = 0; i < 3; i++) {
					try {
						s = new Socket(s.getInetAddress().getHostAddress(), s.getPort());
						flag1 = true;
						break;
					} catch (UnknownHostException e) {
						e.printStackTrace();
						log.debug("取得socket连接失败");
						SocketPoolableObjectFactory.closeSocket(s);
					} catch (IOException e) {
						e.printStackTrace();
						log.debug("取得socket连接失败");
						SocketPoolableObjectFactory.closeSocket(s);
					}
				}
				if (flag1) {// 如果建立socket成功
					flag = sendInner(request, s ,encoding);
					if (!flag) {
						log.debug("取得socket连接失败");
						throw new Exception("取得socket连接失败");
					}
				} else {
					log.debug("取得socket连接失败");
					throw new Exception("取得socket连接失败");
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.sop.returnObject(s);
		}
		return true;
	}
	
	/**
	 * @Definition: 发送socket数据，如果发送失败那么就关闭此连接
	 * @author: chenyongqiang
	 * @Date: 2015年10月15日
	 * @param request
	 * @param s
	 * @param encoding
	 * @return
	 */
	private boolean sendInner(SocketRequest request, Socket s, String encoding) {
		OutputStream os = null;
//		BufferedReader br = null;
//		InputStream is = null;
		boolean flag = true;
		try {//保证数据完整性的两种传输方式：1.加上头部，声明流的长度，服务器接收之后，解析头部，根据长度获取完整流  2.加上特殊标示
			os = s.getOutputStream();
			//os.write("<![start]>\r\n".getBytes(encoding));
			if (encoding == null) {
				encoding = Constant.ENCODING_UTF;
			}
			os.write(request.toString().getBytes(encoding));
			os.flush();
			//接收返回值
//			br = new BufferedReader(new InputStreamReader(s.getInputStream(), Constant.ENCODING_UTF));
//			while (true) {
//				String line = br.readLine();
//				System.out.println(line);
//			}
			
			
//			is = s.getInputStream();
//			byte[] bs = new byte[1024];
//			is.read(bs);
//			while (is.read(bs) != 0) {
//				System.out.println(new String(bs, "gkb"));
//			}
		} catch (IOException e) {
			flag = false;
			/*try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}*/
			if (os != null) {// 如果出现异常关闭连接
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			SocketPoolableObjectFactory.closeSocket(s);
			e.printStackTrace();
		} finally {
			
		}
		return flag;
	}

	public static void main(String args[]) {
		SocketClient sc = null;
		try {
//			sc = SocketFactory.getClient(SocketFactory.WEIXIN_STUDENT);
//			for (int i = 0; i < 1; i++) {
//				String openId = "oHMTmjlTXZJDVB4NqL6IAb-7S4jQ";
//				Map<String, String> dataMap = new HashMap<String, String>();
//				dataMap.put("openid", openId);
//				dataMap.put("stage", "笔试");
//				dataMap.put("studentName", "龙哥同学");
//				dataMap.put("companyName", "腾讯");
//				dataMap.put("positionName", "web前端工程师");
//				dataMap.put("date", "2014年7月21日 18:36");
//				String tmp = WeixinTemplateUtil.getTemplate("delivery_pass", dataMap);
//				SocketRequest request = new SocketRequest(new SocketHeader("/notice/send"), tmp);
//				sc.send(request, Constant.ENCODING_UTF);
//			}
			
			sc = SocketFactory.getClient(SocketFactory.WWW_STUDENT);
			String json = "{\"accId\":795,\"activateStatus\":1,\"apList\":[],\"createDate\":{\"date\":4,\"day\":1,\"hours\":12,\"minutes\":57,\"month\":0,\"seconds\":13,\"time\":1451883433298,\"timezoneOffset\":-480,\"year\":116},\"email\":\"frafemurach999@163.com\",\"gList\":[],\"groupId\":0,\"headImg\":\"\",\"loginName\":\"\",\"mobile\":\"\",\"modifyDate\":null,\"nickname\":\"\",\"parentId\":0,\"pwd\":\"\",\"realName\":\"库布里克\",\"resumeCompleteness\":0,\"salt\":\"\",\"source\":1,\"status\":1,\"type\":1,\"unionId\":0}";
			SocketRequest request = new SocketRequest(new SocketHeader("/student/add"), json);
			sc.send(request, Constant.ENCODING_UTF);
			
//			Thread.sleep(1000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
