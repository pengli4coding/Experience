package com.pl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.pl.dao.PersonDao;
import com.pl.pojo.Person;

@Repository
public class PersonDaoImpl implements PersonDao{
	
	@Autowired
	private MongoTemplate mongoTemplate; // mongodb模板
	
	@Override
	public void savePerson(Person person) {
		this.mongoTemplate.save(person);
	}

}
