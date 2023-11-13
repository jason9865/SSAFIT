package com.ssafit.comment.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CommentLike {
	private int commentLikeId;
	private int userSeq;
	private int commentId;

	@Builder
	public CommentLike(final int commentLikeId, final int userSeq, final int commentId) {
		this.commentLikeId = commentLikeId;
		this.userSeq = userSeq;
		this.commentId = commentId;
	}
	
}
