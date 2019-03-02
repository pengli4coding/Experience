package com.pl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileReaderDemo {

	public static void main(String[] args) {
		initFile("一二三四五abcde一二三");
		FileReader reader = null;
		try {
			// 指定要读取的文件位置
			File file = new File("source.txt");
			// 创建字符输入流
			reader = new FileReader(file);
			// 创建一个字符数组用以保存读取到的字符
			char[] buffer = new char[5];
			// 创建一个int类型的变量用以接收read(buffer)的返回值，这个返回值代表从流中写入到字符数组的字符数
			int len = -1;
			/*
			 * 举个例子：文件中的内容 为“一二三四五abcde一二三”，将每次读取到的内容写入到长度为5的字符数组中，相对于每次读取5个字符
			 *    第一次读取的时候：len为5，buffer中的内容为“一二三四五”
			 *    第二次读取的时候：len为5，buffer中的内容为“abcde”
			 *    第三次读取的时候：len为3，buffer中的内容为“一二三de”，字符数组中最后的两个字符“de”是上一次读取的时候保留下来的字符
			 *    第四次读取的时候：len为-1，这是因为已经读到了文件的末尾了
			 */
			// 一个字符一个字符地读取，读取到-1的时候为文件结束，注意这里读取中文是没问题的
			while ((len = reader.read(buffer)) != -1) {
				System.out.print(new String(buffer, 0, len));// 这里不能直接用new String(buffer),是因为倒数第二次读取的时候会有上一次读取下来的残留字符
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					// 关流
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 初始化文件内容
	 * 
	 * @param str
	 */
	private static void initFile(String str) {
		FileWriter writer = null;
		try {
			// 指定要写入的文件位置
			File file = new File("source.txt");
			// 创建字符输出流，第二个参数为false的话则表示以覆盖的方式写入内容到文件
			writer = new FileWriter(file, false);
			writer.write(str);
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
