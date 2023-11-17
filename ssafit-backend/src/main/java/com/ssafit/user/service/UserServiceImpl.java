package com.ssafit.user.service;

import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.dto.request.UserLoginRequest;
import com.ssafit.user.model.dto.request.UserModifyRequest;
import com.ssafit.user.model.dto.request.UserRegistRequest;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Override
	public List<UserResponse> getUserList() {
		return userDao.selectAll().stream()
				.map(UserResponse::from)
				.collect(toList());

	}
	@Override
	public UserResponse searchByUserSeq(int userSeq) {
		return UserResponse.from(userDao.selectByUserSeq(userSeq));
	}

	@Override
	public User searchByUserId(String id) {
		return userDao.selectByUserId(id);
	}

	@Override
	public User searchByName(String name) {
		return userDao.selectByName(name);
	}

	@Override
	@Transactional
	public boolean registUser(UserRegistRequest request) {
		User newUser = User.builder()
				.userId(request.getUserId())
				.userPwd(request.getUserPwd())
				.userName(request.getUserName())
				.nickName(request.getNickName())
				.email(request.getEmail())
				.build();
		return userDao.insertUser(newUser) > 0;
	}

	@Override
	@Transactional
	public boolean modifyUser(UserModifyRequest request, int userSeq) {
		User updatedUser = User.builder()
				.userSeq(userSeq)
				.userPwd(request.getUserPwd())
				.userName(request.getUserName())
				.nickName(request.getNickName())
				.email(request.getEmail())
				.build();
		return userDao.updateUser(updatedUser) > 0;
	}

	@Override
	@Transactional
	public boolean removeUser(int userSeq) {
		return userDao.deleteUser(userSeq) > 0;
	}
	
	@Override
	public UserResponse login(UserLoginRequest loginRequest) {
		User temp = userDao.selectByUserId(loginRequest.getUserId());
		if (temp != null && temp.getUserPwd().equals(loginRequest.getUserPwd()))
			return UserResponse.from(temp);
		return null;
	}


}
