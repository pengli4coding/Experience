package com.pl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

public class MongoApp {

	private static final Log log = LogFactory.getLog(MongoApp.class);

	  public static void main(String[] args) throws Exception {
		  
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		MongoTemplate template = ctx.getBean("mongoTemplate",MongoTemplate.class);
		
		String dateStr1 = "1990-09-02";
		String dateStr2 = "1991-09-02";
		String dateStr3 = "1992-09-02";
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date data1 = sf.parse(dateStr1);
		Date data2 = sf.parse(dateStr2);
		Date data3 = sf.parse(dateStr3);
		
		Person p1 = new Person("pl", data1);
		Person p2 = new Person("plpl", data2);
		Person p3 = new Person("plplpl", data3);

		template.insert(p1);
		template.insert(p2);
		template.insert(p3);

		BasicQuery query = new BasicQuery("{\"name\" : \"plpl\"}");
		List<Person> list = template.find(query, Person.class);
		System.out.println(list);

		query = new BasicQuery("{$or:[{\"name\" : \"plpl\"},{\"name\" : \"plplpl\"}]}");
		list = template.find(query, Person.class);
		System.out.println(list);
		
		query = new BasicQuery("{$or:[{\"name\" : \"plpl\"},{\"name\" : \"plplpl\"}]}");
		long count = template.count(query, Person.class);
		System.out.println(count);

	  }
}
