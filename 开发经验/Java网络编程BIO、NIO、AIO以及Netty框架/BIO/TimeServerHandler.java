package com.pl.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TimeServerHandler implements Runnable {

	private Socket socket;

	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String currentTime = null;
			String body = null;
			while(true) {
				body = in.readLine();
				if(body == null) {// 如果已经读到输入流的尾部，则返回值为null，推出循环
					break;
				}
				System.out.println("服务器接收到消息为：" + body);
				try {
					TimeUnit.SECONDS.sleep(10);// 模拟处理请求的时间非常的长
				} catch (InterruptedException e) {
				}
				currentTime = "get time".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "指令错误";
				out.println(currentTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != out) {
				out.close();
				out = null;
			}
			if(null != this.socket) {
				try {
					this.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.socket = null;
			}
		}
	}

}
