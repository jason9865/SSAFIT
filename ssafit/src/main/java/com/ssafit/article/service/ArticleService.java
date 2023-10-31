package com.ssafit.article.service;

import com.ssafit.article.model.dto.Article;

import java.util.List;

public interface ArticleService {
	
	List<Article> getArticleList();
		
	Article readArticle(int article_id);
	
	Article searchArticleById(int article_id);
		
	boolean writeArticle(Article article);
	
	boolean modifyArticle(Article article);
	
	boolean deleteArticle(int article_id);


}
