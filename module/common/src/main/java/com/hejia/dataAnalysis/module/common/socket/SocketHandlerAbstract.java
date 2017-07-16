package com.hejia.dataAnalysis.module.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.hejia.dataAnalysis.module.common.Constant;

/**
 * @Description: socket线程处理类
 * @author: chenyongqiang
 * @Date: 2015年9月16日
 * @version: 1.0
 */
public abstract class SocketHandlerAbstract implements Runnable {

	protected Socket socket;

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		//获取内容
		this.getContent();
	}
	
	public void getContent() {
		BufferedReader br = null;
//		OutputStream os = null;
		try {
			br = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), Constant.ENCODING_UTF));
//			os = this.socket.getOutputStream();
			StringBuffer buf = new StringBuffer();
			while (true) {
				String line = br.readLine();
				if (line != null && line.equals("<![end]>")) {
					this.handle(buf.toString());
					buf.delete(0, buf.length());
//					//返回消息
//					os.write("已经结束到消息".getBytes(Constant.ENCODING_UTF));
//					os.flush();
				} else {
					buf.append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			try {
//				os.close();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年9月17日
	 * @param content
	 */
	public abstract void handle(String content);
}
