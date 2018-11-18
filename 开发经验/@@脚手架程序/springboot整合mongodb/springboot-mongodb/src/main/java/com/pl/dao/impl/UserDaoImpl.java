package com.pl.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.pl.dao.UserDao;
import com.pl.pojo.User;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private MongoTemplate mongoTemplate; // mongodb模板

	@Override
	public void saveUser(User user) {
		this.mongoTemplate.save(user);

	}

	@Override
	public User findById(Long id) {
		return this.mongoTemplate.findOne(query(where("id").is(id)), User.class);
	}

	@Override
	public List<User> findByAge(int minAge, int maxAge) {
		return this.mongoTemplate.find(query(where("age").gte(minAge).lte(maxAge)), User.class);
	}

	@Override
	public void updateUser(User user) {
		this.mongoTemplate.updateFirst(query(where("id").is(user.getId())),
				update("name", user.getName()).set("age", user.getAge()), User.class);
	}

	@Override
	public void deleteUserById(Long id) {
		this.mongoTemplate.remove(query(where("id").is(id)), User.class);
	}

}
