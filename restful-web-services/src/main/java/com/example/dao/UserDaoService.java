package com.example.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 4;

	static {
		users.add(new User(1, "Vaibhav", new Date()));
		users.add(new User(2, "Karan", new Date()));
		users.add(new User(3, "John", new Date()));
		users.add(new User(4, "Donald", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getUserId() == 0) {
			user.setUserId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}

	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getUserId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
