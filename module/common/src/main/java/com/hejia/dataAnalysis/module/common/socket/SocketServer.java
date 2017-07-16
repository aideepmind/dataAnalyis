package com.hejia.dataAnalysis.module.common.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.hejia.dataAnalysis.module.common.utils.thread.ThreadManagerPool;

/**
 * @Description: socket服务器（目前支持多个不同端口的服务），还没考虑阻塞的情况，后期开发
 * @author: chenyongqiang
 * @Date: 2015年9月16日
 * @version: 1.0
 */
public class SocketServer {

	private static Logger log = Logger.getLogger(SocketServer.class.getName());
	
	/**
	 * @Definition: 开通监听服务
	 * @author: chenyongqiang
	 * @Date: 2015年9月16日
	 * @param ip
	 * @param port
	 * @param backlog
	 */
	public static void open(String ip, int port, int backlog, SocketHandlerAbstract sha) {
		ServerSocket ss = null;
		try {
			InetSocketAddress endpoint = new InetSocketAddress(InetAddress.getByName(ip), port);
			ss = new ServerSocket();
			ss.bind(endpoint, backlog);
			log.info("开通端口：" + port + "的服务成功。");
			while(true){
				Socket s = ss.accept();
				try {
					sha.setSocket(s);
					ThreadManagerPool.addThread(sha);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.debug("开通端口：" + port + "的服务失败，因为端口已被其他服务器进程占用。");
		} finally {
			close(ss);
		}
	}
	
	/**
	 * @Definition: 关闭监听服务
	 * @author: chenyongqiang
	 * @Date: 2015年9月16日
	 * @param port
	 */
	public static void close(ServerSocket ss) {
		try {
			ss.close();
			log.info("关闭端口：" + ss.getInetAddress().getLocalHost() + "的服务成功。");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				log.debug("关闭端口：" + ss.getInetAddress().getLocalHost() + "的服务失败。");
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		//SocketServer.open("192.168.1.106", 5678, 1);
	}
}
