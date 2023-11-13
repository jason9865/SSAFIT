package com.ssafit.user.model.entity;

import lombok.*;

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

	@Builder
	public User(final int userSeq, final String userId, final String userPwd, final String userName,
				final String nickName, final String email, final int userRank, final String joinedAt){
		this.userSeq= userSeq;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.nickName = nickName;
		this.email = email;
		this.userRank = userRank;
		this.joinedAt = joinedAt;
	}




	
}
