package com.ssafit.comment.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLike {
	private int commentLikeId;
	private int userSeq;
	private int commentId;
	
	
}
