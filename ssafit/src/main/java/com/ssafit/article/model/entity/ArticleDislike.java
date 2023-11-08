package com.ssafit.article.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDislike {
	int id;
	int userSeq;
	int articleId;

	
}
