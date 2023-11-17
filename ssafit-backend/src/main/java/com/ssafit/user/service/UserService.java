package com.ssafit.user.service;

import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.request.UserModifyRequest;
import com.ssafit.user.model.dto.request.UserRegistRequest;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.User;

import java.util.List;

public interface UserService {

	List<UserResponse> getUserList();

	UserResponse searchByUserSeq(int userSeq);
	
	User searchByUserId(String userId);
	
	User searchByName(String name);
	
	boolean registUser(UserRegistRequest request);
	
	boolean modifyUser(UserModifyRequest request, int userSeq);
	
	boolean removeUser(int userSeq);
	
	UserResponse login(UserLoginRequest loginRequest);
	
}
