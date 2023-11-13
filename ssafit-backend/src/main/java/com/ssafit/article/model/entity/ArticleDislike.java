package com.ssafit.article.model.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class ArticleDislike {
	private int articleDislikeId;
	private int userSeq;
	private int articleId;


	@Builder
	public ArticleDislike(final int articleDislikeId, final int userSeq, final int articleId){
		this.articleDislikeId = articleDislikeId;
		this.userSeq = userSeq;
		this.articleId = articleId;
	}

}
