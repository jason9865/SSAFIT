package com.ssafit.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
	private int userSeq;
	private String userId;
	private String userPwd;
	private String userName;
	private String nickName;
	private String email;
	private int userRank; // 관리자 or 유저
	private String joinedAt;


	
}
