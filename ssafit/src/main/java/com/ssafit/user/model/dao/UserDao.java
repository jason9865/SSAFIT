package com.ssafit.user.model.dao;

import com.ssafit.user.model.dto.User;

import java.util.List;

public interface UserDao {
	
	List<User> selectAll();
	
	// user_seq으로 유저 찾기
	User selectByUserSeq(int userSeq);
	
	// id로 유저 찾기
	User selectByUserId(String userId);
	
	// 이름으로 유저 찾기
	User selectByName(String name);
	
	// 회원가입
	int insertUser(User user);
	
	// 유저 정보 수정
	void updateUser(User user);
	
	// 유저 정보 삭제 -> 관리자만 가능
	void deleteUser(int userSeq);
	
	
}
