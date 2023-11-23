package com.ssafit.user.model.dao;

import com.ssafit.user.model.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
	
	List<User> selectAll();
	
	// user_seq으로 유저 찾기
	User selectByUserSeq(int userSeq);
	
	// id로 유저 찾기
	User selectByUserId(String userId);

	// 이메일로 유저 찾기
	User selectByEmail(String email);

	// 닉네임으로 유저 찾기
	User selectByNickName(String nickName);

	// 이름으로 유저 찾기
	User selectByName(String name);
	
	// 회원가입
	int insertUser(User user);
	
	// 유저 정보 수정
	int updateUser(User user);
	
	// 유저 정보 삭제 -> 관리자만 가능
	int deleteUser(int userSeq);
	
	// 비밀번호 변경
	int updatePwd(Map<String, String> map);
	
	
}
