package com.pl.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileDemo {

	public static void main(String[] args) {
		BufferedReader bReader = null;
		BufferedWriter bWriter = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
			File srcFile = new File("C:/source.txt");
			File destFile = new File("D:/destination.txt");
			reader = new FileReader(srcFile);
			writer = new FileWriter(destFile, false);
			bReader = new BufferedReader(reader);
			bWriter = new BufferedWriter(writer);
			String lineStr = null;
			while ((lineStr = bReader.readLine()) != null) {
				bWriter.write(lineStr);
				bWriter.newLine();
				bWriter.flush();
			}
			System.out.println("文件拷贝成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bWriter) {
				try {
					bWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != bReader) {
				try {
					bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
