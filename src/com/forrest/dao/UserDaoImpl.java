package com.forrest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forrest.mapper.UserMapper;
import com.forrest.model.User;

@Component
public class UserDaoImpl implements UserDao{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUserId(id);
	}
}
