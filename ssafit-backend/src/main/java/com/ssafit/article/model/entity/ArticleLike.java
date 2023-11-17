package com.ssafit.article.model.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class ArticleLike {
	private int articleLikeId;
	private	int userSeq;
	private int articleId;

	@Builder
	public ArticleLike(final int articleLikeId, final int userSeq, final int articleId){
		this.articleLikeId = articleLikeId;
		this.userSeq = userSeq;
		this.articleId = articleId;
	}
}
