package com.refferal.dao;

import com.refferal.entity.User;

public interface UserDao {

	public int insertOrUpdate(User user);

	public User selectByOpenID(String openID);

	public int insert(User user);
	
}
