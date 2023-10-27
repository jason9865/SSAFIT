package com.ssafy.ws.model.dto;

public class User {
	int userSeq;
	String id;
	String userPwd;
	String userName;
	String nickName;
	String email;
	int userRank; // 관리자 or 유저
	String joinedAt;
	
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserRank() {
		return userRank;
	}
	public void setUserRank(int userRank) {
		this.userRank = userRank;
	}
	public String getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(String joinedAt) {
		this.joinedAt = joinedAt;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userPwd=" + userPwd + ", userName=" + userName + ", nickName=" + nickName
				+ ", email=" + email + ", userRank=" + userRank + ", joinedAt=" + joinedAt + "]";
	}
	
}
