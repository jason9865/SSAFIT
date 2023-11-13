package com.ssafit.comment.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CommentDislike {
	private int commentDisLikeId;
	private int userSeq;
	private int commentId;

	@Builder
	CommentDislike(final int commentDisLikeId, final int userSeq, final int commentId){
		this.commentDisLikeId = commentDisLikeId;
		this.userSeq = userSeq;
		this.commentId = commentId;
	}

	
}
