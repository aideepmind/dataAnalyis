package com.hejia.dataAnalysis.module.common.socket;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.log4j.Logger;

/**
 * @Description: socket对象池工厂
 * @author: chenyongqiang
 * @Date: 2015年10月16日
 * @version: 1.0
 */
public class SocketPoolableObjectFactory extends BasePoolableObjectFactory {
	
	private static Logger log = Logger.getLogger(SocketPoolableObjectFactory.class.getName());

	public String ip;

	public int port;
	
	/**
	 * @Definition: 创建对象
	 * @author: chenyongqiang
	 * @Date: 2015年9月16日
	 * @return
	 * @throws Exception
	 */
	public Object makeObject() throws Exception {
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
			return socket;
		}catch (Exception e) {
			closeSocket(socket);
			log.debug("取得socket连接失败");
			throw new Exception("打开socket失败!");
		}
	}
	
	/**
	 * @Definition: 关闭连接
	 * @author: chenyongqiang
	 * @Date: 2015年9月16日
	 * @param socket
	 */
	public static void closeSocket(Socket socket) {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	 public void destroyObject(Object arg0) throws Exception {
		 
		 Socket s = (Socket)arg0;
		 if(s != null){
			 s.close();
		 }
			
	 }
}
