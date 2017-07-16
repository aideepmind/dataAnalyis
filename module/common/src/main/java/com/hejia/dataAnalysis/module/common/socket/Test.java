package com.hejia.dataAnalysis.module.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Test {
	public static void main(String [] args) throws IOException {
		Socket sock = new Socket("221.207.229.3", 33335);
		// 创建一个写线程
		new TelnetWriter(sock.getOutputStream()).start();
		// 创建一个读线程
		new TelnetReader(sock.getInputStream()).start();
	}
}
class TelnetWriter extends Thread {
	private PrintStream out;
	public TelnetWriter(OutputStream out) {
		this.out = new PrintStream(out);
	}
	public void run() {
		try {
			// 包装控制台输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			// 反复将控制台输入写到Telnet服务程序
			while (true)  out.println(in.readLine());
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
}
class TelnetReader extends Thread {
	private InputStreamReader in;
	public TelnetReader(InputStream in) {
		this.in = new InputStreamReader(in);
	}
	public void run() {
		try {
			// 反复将Telnet服务的反馈信息显示在控制台窗口中
			while (true) {
				// 从Telnet服务程序读取数据
				int b = in.read();
				if (b == -1)  System.exit(0);
				// 将数据显示在控制台屏幕上
				System.out.print((char) b);
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}}