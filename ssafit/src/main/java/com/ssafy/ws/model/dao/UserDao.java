package com.ssafy.ws.model.dao;

import java.util.List;

import com.ssafy.ws.model.dto.User;

public interface UserDao {
	
	List<User> selectAll();
	
	// user_seq으로 유저 찾기
	User selectByUserSeq(int userSeq);
	
	// id로 유저 찾기
	User selectByUserId(String id);
	
	// 이름으로 유저 찾기
	User selectByName(String name);
	
	// 회원가입
	int insertUser(User user);
	
	// 유저 정보 수정
	void updateUser(User user);
	
	// 유저 정보 삭제 -> 관리자만 가능
	void deleteUser(int userSeq);
	
	
}
