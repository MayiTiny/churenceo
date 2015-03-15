package com.refferal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.dao.UserDao;
import com.refferal.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int insertOrUpdate(User user) {
		return userDao.insertOrUpdate(user);
	}
	
}
