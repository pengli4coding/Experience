package com.pl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileReaderDemo {

	public static void main(String[] args) {
		FileReader reader = null;
		try {
			// 指定要读取的文件位置
			File file = new File("source.txt");
			// 创建字符输入流
			reader = new FileReader(file);
			int ch = -1;
			// 一个字符一个字符地读取，读取到-1的时候为文件结束，注意这里读取中文是没问题的
			while ((ch = reader.read()) != -1) {
				System.out.print((char) ch);// 转换为char之后输出到控制台
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
}
