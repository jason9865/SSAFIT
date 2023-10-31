package com.ssafit.user.service;

import com.ssafit.user.model.dto.User;

import java.util.List;

public interface UserService {
	
	List<User> getList();
	
	User searchByUserSeq(int userSeq);
	
	User searchByUserId(String id);
	
	User searchByName(String name);
	
	int registUser(User user);
	
	void modifyUser(User user);
	
	void removeUser(int userSeq);
	
	User login(User user);
	
}
