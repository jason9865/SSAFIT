package com.ssafit.article.service;

import com.ssafit.article.model.dao.ArticleDao;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleDao articleDao;
	private final UserDao userDao;
	
	@Override
	public List<Article> getArticleList() {
		return articleDao.selectAll();
	}


	@Override // 조회 수 증가 O
	public ArticleResponse readArticle(int articleId) {
		Article article = searchArticleById(articleId);
		articleDao.updateViewCnt(articleId);
		User user = userDao.selectByUserSeq(article.getUserSeq());
		return ArticleResponse.of(article,user);
	}
	
	@Override // 조회 수 증가 X
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
