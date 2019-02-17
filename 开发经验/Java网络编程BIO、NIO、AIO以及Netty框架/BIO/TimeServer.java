package com.pl.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args) {
		// 监听端口号，默认为8080
		int port = 8080;
		if (null != args && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		ServerSocket server = null;

		try {
			server = new ServerSocket(port);
			System.out.println("服务端启动，监听端口号为：" + port);
			Socket socket = null;
			while (true) {
				socket = server.accept();// 开始监听是否有请求过来，如果没有请求过来，这里会阻塞
				System.out.println("有新的请求过来，开始创建一个新的线程处理请求");
				new Thread(new TimeServerHandler(socket)).start();// 创建一个新的线程处理请求
				System.out.println("创建新线程完毕");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != server) {
				System.out.println("服务端关闭");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server = null;
			}
		}
	}
}
