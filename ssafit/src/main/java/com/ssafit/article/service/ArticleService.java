package com.ssafit.article.service;

import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;

import java.util.List;

public interface ArticleService {
	
	List<Article> getArticleList();

	ArticleResponse readArticle(int articleId);
	
	Article searchArticleById(int articleId);
		
	boolean writeArticle(Article article);
	
	boolean modifyArticle(Article article);
	
	boolean deleteArticle(int articleId);


}
