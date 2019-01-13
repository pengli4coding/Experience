package com.pl.useBasicQuery;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import com.pl.pojo.Person;

@Component
public class BasicQueryUtil {
	
	@Autowired
	private MongoTemplate _mongoTemplate;
	
	private static MongoTemplate mongoTemplate;
	
	@PostConstruct
	public void init() {
		BasicQueryUtil.mongoTemplate = this._mongoTemplate;
	}
	
	// 根据mongodb自动生成的objectId来查询
	public static Person objectIdQuery(String objectId,String collectionName) {
		// 组装查询条件
		Document document = new Document();
		document.put("_id", new ObjectId(objectId));// 根据mongodb自动生成的objectId来查询
		//document.put("name", "张三1");
		BasicQuery basicQuery = new BasicQuery(document);
		// 查询
		Person person = mongoTemplate.findOne(basicQuery, Person.class, collectionName);
		return person;
	}
	
}
