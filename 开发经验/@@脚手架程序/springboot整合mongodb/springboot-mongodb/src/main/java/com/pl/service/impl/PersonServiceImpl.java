package com.pl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.dao.PersonDao;
import com.pl.pojo.Person;
import com.pl.service.PersonService;
@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public void savePerson(Person person) {
		this.personDao.savePerson(person);
	}
}
