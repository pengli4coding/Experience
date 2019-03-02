package com.pl.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) {
		initFile();
		BufferedReader bReader = null;
		FileReader reader = null;
		try {
			// 指定要读取的文件位置
			File file = new File("source.txt");
			// 创建字符输入流
			reader = new FileReader(file);
			// 创建高效缓冲区输出流
			// 高效缓冲区输出流的缓存区默认大小为new char[8192]
			bReader = new BufferedReader(reader);
			String lineStr = null;
			// 利用BufferedReader的扩展方法readLine()每一次读取一行内容
			while ((lineStr = bReader.readLine()) != null) {
				System.out.println(lineStr);
			}
			// 以下这种是传统的读取方法
			// char[] array = new char[5];
			// int len = -1;
			// while ((len = bReader.read(array)) != -1) {
			// System.out.print(new String(array, 0, len));
			// }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bReader) {
				try {
					bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param str
	 */
	private static void initFile() {
		FileWriter writer = null;
		try {
			// 指定要写入的文件位置
			File file = new File("source.txt");
			// 创建字符输出流
			writer = new FileWriter(file, false);
			for (int i = 0; i < 100; i++) {
				// 往字符输出流的缓冲区中写入字符，默认缓冲区的大小是new char[1024]
				writer.write("hello IO " + i + System.lineSeparator());
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
