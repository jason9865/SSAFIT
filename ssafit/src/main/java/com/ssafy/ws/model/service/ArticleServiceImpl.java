package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.ArticleDao;
import com.ssafy.ws.model.dto.Article;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	private final ArticleDao articleDao;
	
	@Autowired
	public ArticleServiceImpl(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	@Override
	public List<Article> getArticleList() {
		return articleDao.selectAll();
	}


	@Override
	public Article readArticle(int article_id) {
		articleDao.updateViewCnt(article_id);
		return articleDao.selectById(article_id);
	}
	
	@Override
	public Article searchArticleById(int article_id) {
		return articleDao.selectById(article_id);
	}
	

	@Override
	public void writeArticle(Article article) {
		articleDao.insertArticle(article);

	}

	@Override
	public void modifyArticle(Article article) {
		articleDao.updateArticle(article);
	}

	@Override
	public void deleteArticle(int article_id) {
		articleDao.deleteArticle(article_id);
	}


}
