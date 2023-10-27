package com.ssafy.ws.model.dto;

public class Article {
	int id;
	String title;
	String content;
	int boardId;
	int userSeq;
	int viewCnt;
	int lft;
	int rgt;
	int depth;
	String createdAt;
	String modifiedAt;
	User userTable;
	Board boardTable;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", boardId=" + boardId
				+ ", userSeq=" + userSeq + ", viewCnt=" + viewCnt + ", lft=" + lft + ", rgt=" + rgt + ", depth=" + depth
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", userTable=" + userTable
				+ ", boardTable=" + boardTable + "]";
	}
	
	
	
	
	
}
