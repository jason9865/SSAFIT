package com.ssafy.ws.model.dto;

public class ArticleLike {
	int id;
	int userSeq;
	int articleId;
	
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
	
	
	@Override
	public String toString() {
		return "ArticleLike [userSeq=" + userSeq + ", articleId=" + articleId + "]";
	}
	
}
