package com.pl.test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FirstDemo {

	public static void main(String[] args) throws Exception {
		// 创建配置实例
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
		cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));// 指定获取模板文件的根目录
		cfg.setDefaultEncoding("UTF-8");// 设置模板文件的编码格式
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		
		// 创建数据模型
		Map<String, Object> root = new HashMap<>();
		root.put("user", "Big Joe");
		Product latest = new Product();
		latest.setUrl("products/greenmouse.html");
		latest.setName("green mouse");
		root.put("latestProduct", latest);
		
		// 整合模板以及数据模型
		Template temp = cfg.getTemplate("my.ftl");
		Writer out = new OutputStreamWriter(System.out);// 指定输出的位置，这里指定的是控制台
		temp.process(root, out);// 执行整合模板以及数据模型并输出
	}
}
