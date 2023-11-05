package com.ssafit.comment.model.dto;

public class CommentLike {
	private int commentLikeId;
	private int userSeq;
	private int commentId;
	
	public int getCommentLikeId() {
		return commentLikeId;
	}
	public void setCommentLikeId(int commentLikeId) {
		this.commentLikeId = commentLikeId;
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
		return "CommentLike [userSeq=" + userSeq + ", commentId=" + commentId + "]";
	}
}
