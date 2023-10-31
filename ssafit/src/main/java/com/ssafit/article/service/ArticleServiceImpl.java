package com.ssafit.article.service;

import com.ssafit.article.model.dao.ArticleDao;
import com.ssafit.article.model.dto.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
	
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
	public Article readArticle(int articleId) {
		articleDao.updateViewCnt(articleId);
		return articleDao.selectById(articleId);
	}
	
	@Override
	public Article searchArticleById(int articleId) {
		return articleDao.selectById(articleId);
	}
	

	@Override
	public boolean writeArticle(Article article) {
		return articleDao.insertArticle(article) > 0;
	}

	@Override
	public boolean modifyArticle(Article article) {
		return articleDao.updateArticle(article) > 0;
	}

	@Override
	public boolean deleteArticle(int articleId) {
		return articleDao.deleteArticle(articleId) > 0;
	}


}
