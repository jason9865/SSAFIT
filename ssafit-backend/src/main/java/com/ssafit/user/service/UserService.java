package com.ssafit.user.service;

import com.ssafit.user.model.dto.request.UserCheckRequest;
import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.request.UserModifyRequest;
import com.ssafit.user.model.dto.request.UserRegistRequest;
import com.ssafit.user.model.dto.response.UserIdCheckResponse;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.User;
import com.ssafit.user.model.entity.Mail;

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
	
	Mail createMailAndChangePwd(String userId, String email);
	
	String getTempPwd();
	
	void sendMail(Mail mail);
	
	void updatePwd(String userId, String pwd);

	UserIdCheckResponse validateId(UserCheckRequest request);

	UserIdCheckResponse validateEmail(UserCheckRequest request);

	UserIdCheckResponse validateNickName(UserCheckRequest request);
}
