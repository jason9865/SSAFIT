package com.ssafit.comment.model.dto;

public class CommentDislike {
	int id;
	int userSeq;
	int commentId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	@Override
	public String toString() {
		return "CommentDislike [userSeq=" + userSeq + ", commentId=" + commentId + "]";
	}

	
}
