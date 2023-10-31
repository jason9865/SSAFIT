package com.ssafit.comment.model.dto;

public class CommentDislike {
	int commentDisLikeId;
	int userSeq;
	int commentId;
	
	public int getCommentDisLikeId() {
		return commentDisLikeId;
	}
	public void setCommentDisLikeId(int id) {
		this.commentDisLikeId = commentDisLikeId;
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
