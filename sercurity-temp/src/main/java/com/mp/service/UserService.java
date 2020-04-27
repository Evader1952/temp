package com.mp.service;


import com.mp.pojo.User;

public interface UserService {

	public User findByName(String name);
	
	public User findById(Integer id);
}
