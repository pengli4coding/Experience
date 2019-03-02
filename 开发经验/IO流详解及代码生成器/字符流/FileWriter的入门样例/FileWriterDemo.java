package com.pl.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {

	public static void main(String[] args) {
		FileWriter writer = null;
		try {
			// 指定要写入的文件位置
			File file = new File("destination.txt");
			// 创建字符输出流，第二个参数为true的话则表示以追加的方式写入内容到文件
			writer = new FileWriter(file, true);
			for (int i = 0; i < 100; i++) {
				// 往字符输出流的缓冲区中写入字符，默认缓冲区的大小是new char[1024]
				writer.write("hello IO" + System.lineSeparator());
				if (i % 10 == 0) {
					// 将缓冲区中的内容写入刷入到磁盘文件中， 并清空缓冲区
					writer.flush();
				}
			}
			// 将缓冲区中的内容写入刷入到磁盘文件中， 并清空缓冲区
			writer.flush();
			System.out.println("写入内容到文件成功!!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					// 关流
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
