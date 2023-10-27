package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.Article;

public interface ArticleService {
	
	List<Article> getArticleList();
		
	Article readArticle(int article_id);
	
	Article searchArticleById(int article_id);
		
	void writeArticle(Article article);
	
	void modifyArticle(Article article);
	
	void deleteArticle(int article_id);


}
