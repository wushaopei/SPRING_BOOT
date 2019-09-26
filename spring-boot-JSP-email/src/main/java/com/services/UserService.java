package com.services;

import com.entities.User;

import java.util.List;


public interface UserService {
	
	List<User> getAll();

    Integer addUser(User user);

    User findByCode(String code);

    void updateUser(User user);
}
