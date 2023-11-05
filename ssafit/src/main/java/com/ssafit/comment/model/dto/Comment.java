package com.ssafit.comment.model.dto;

import com.ssafit.user.model.dto.User;

public class Comment {
	private int commentId;
	private String content;
	private int userSeq;
	private int articleId;
	private int boardId;
	private String createdAt;
	private User userTable;

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
				"commentId=" + commentId +
				", content='" + content + '\'' +
				", userSeq=" + userSeq +
				", articleId=" + articleId +
				", boardId=" + boardId +
				", createdAt='" + createdAt + '\'' +
				", userTable=" + userTable +
				'}';
	}
}
