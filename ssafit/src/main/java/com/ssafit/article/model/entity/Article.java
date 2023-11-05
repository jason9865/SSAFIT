package com.ssafit.article.model.entity;

import com.ssafit.board.model.entity.Board;
import com.ssafit.user.model.entity.User;

public class Article {
	private int articleId;
	private String title;
	private String content;
	private int boardId;
	private int userSeq;
	private int viewCnt;
	private String createdAt;
	private String modifiedAt;
	private User userTable;
	private Board boardTable;
	

	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public User getUserTable() {
		return userTable;
	}
	public void setUserTable(User userTable) {
		this.userTable = userTable;
	}
	
	public Board getBoardTable() {
		return boardTable;
	}
	public void setBoardTable(Board boardTable) {
		this.boardTable = boardTable;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", content=" + content + ", boardId=" + boardId
				+ ", userSeq=" + userSeq + ", viewCnt=" + viewCnt
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", userTable=" + userTable
				+ ", boardTable=" + boardTable + "]";
	}
	
	
	
	
	
}
