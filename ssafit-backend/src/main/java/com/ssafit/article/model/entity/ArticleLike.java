package com.ssafit.article.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleLike {
	int id;
	int userSeq;
	int articleId;
}
