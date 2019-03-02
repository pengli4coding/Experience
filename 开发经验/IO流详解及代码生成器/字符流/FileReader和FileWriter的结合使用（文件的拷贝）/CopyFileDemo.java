package com.pl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDemo {

	public static void main(String[] args) {
		FileReader reader = null;
		FileWriter writer = null;
		try {
			File srcFile = new File("C:/source.txt");
			File destFile = new File("D:/destination.txt");
			reader = new FileReader(srcFile);
			writer = new FileWriter(destFile, false);
			char[] buffer = new char[8];
			int len = -1;
			while ((len = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, len);
				writer.flush();
			}
			System.out.println("文件拷贝成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
