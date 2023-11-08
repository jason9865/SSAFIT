package com.ssafit.article.model.entity;

import com.ssafit.board.model.entity.Board;
import com.ssafit.user.model.entity.User;
import lombok.*;

@Data
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Article {
	private int articleId;
	private String title;
	private String content;
	private int boardId;
	private int userSeq;
	private int viewCnt;
	private String createdAt;
	private String modifiedAt;

	@Builder
	public Article(
			final int articleId, final String title, final String content, final int boardId, final int userSeq,
			final int viewCnt, final String createdAt, final String modifiedAt){
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.boardId = boardId;
		this.userSeq = userSeq;
		this.viewCnt = viewCnt;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	
	
}
