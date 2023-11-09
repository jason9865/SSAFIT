package com.ssafit.article.service;

import com.ssafit.article.model.dto.request.ArticleModifyDto;
import com.ssafit.article.model.dto.request.ArticleRegistDto;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;

import java.util.List;

public interface ArticleService {
	
	List<ArticleResponse> getArticleList();

	ArticleResponse getArticle(int articleId);
	
	ArticleResponse readArticle(int articleId);

	boolean writeArticle(ArticleRegistDto articleRegistDto, int userSeq);

	boolean modifyArticle(ArticleModifyDto articleModifyDto, int articleId, int userSeq);

	boolean deleteArticle(int articleId);



}
