package com.ssafit.comment.model.dto;

import com.ssafit.user.model.dto.User;

public class Comment {
	int id;
	String content;
	int userSeq;
	int articleId;
	int boardId;
	int lft;
	int rgt;
	int depth;
	String createdAt;
	User userTable;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getLft() {
		return lft;
	}
	public void setLft(int lft) {
		this.lft = lft;
	}
	public int getRgt() {
		return rgt;
	}
	public void setRgt(int rgt) {
		this.rgt = rgt;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public User getUserTable() {
		return userTable;
	}
	public void setUserTable(User userTable) {
		this.userTable = userTable;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", content='" + content + '\'' +
				", userSeq=" + userSeq +
				", articleId=" + articleId +
				", boardId=" + boardId +
				", lft=" + lft +
				", rgt=" + rgt +
				", depth=" + depth +
				", createdAt='" + createdAt + '\'' +
				", userTable=" + userTable +
				'}';
	}
}
