package com.ssafy.ws.model.dto;

public class ArticleDislike {
	int id;
	int userSeq;
	int articleId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUser_seq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticle_id(int articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "ArticleDislike [userSeq=" + userSeq + ", articleId=" + articleId + "]";
	}
	
	
	
}
