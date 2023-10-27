package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.User;

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
