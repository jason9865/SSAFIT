package com.ssafit.comment.model.entity;

import com.ssafit.user.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {
	private int commentId;
	private String content;
	private int userSeq;
	private int articleId;
	private int boardId;
	private String createdAt;

	@Builder
	public Comment(
			final int commentId, final String content, final int userSeq, final int articleId,
			final int boardId, final String createdAt) {
		this.commentId = commentId;
		this.content = content;
		this.userSeq = userSeq;
		this.articleId = articleId;
		this.boardId = boardId;
		this.createdAt = createdAt;
	}
}
