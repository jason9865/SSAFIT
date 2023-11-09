package com.ssafit.user.service;

import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.User;

import java.util.List;

public interface UserService {
	
	List<User> getList();
	
	User searchByUserSeq(int userSeq);
	
	User searchByUserId(String userId);
	
	User searchByName(String name);
	
	int registUser(User user);
	
	void modifyUser(User user);
	
	void removeUser(int userSeq);
	
	UserResponse login(UserLoginRequest loginRequest);
	
}
