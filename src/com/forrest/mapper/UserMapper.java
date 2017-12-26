package com.forrest.mapper;

import com.forrest.model.User;

public interface UserMapper {
	public User selectUserByUserId(int userId);

}
