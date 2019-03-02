package com.pl.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {

	public static void main(String[] args) {
		BufferedWriter bWriter = null;
		FileWriter writer = null;
		try {
			// 指定要写入的文件位置
			File file = new File("destination.txt");
			// 创建字符输出流，第二个参数为true的话则表示以追加的方式写入内容到文件
			writer = new FileWriter(file, true);
			// 创建高效缓冲区输出流，将字符输出流对象作为构造器的参数传入
			// 高效缓冲区输出流的缓存区默认大小为new char[8192]
			bWriter = new BufferedWriter(writer);
			for (int i = 0; i < 100; i++) {
				bWriter.write("hello IO");
				bWriter.newLine();// 这是BufferedWriter的一个扩展方法，用以写入换行符
				// 将缓冲区中的内容写入刷入到磁盘文件中， 并清空缓冲区
				bWriter.flush();
			}
			// 将缓冲区中的内容写入刷入到磁盘文件中， 并清空缓冲区
			bWriter.flush();
			System.out.println("写入内容到文件成功!!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关流，注意这里自需要关闭高效缓冲区BufferedWriter的流就可以了，FileWriter的流也会跟着自动关闭
			if (null != bWriter) {
				try {
					bWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
