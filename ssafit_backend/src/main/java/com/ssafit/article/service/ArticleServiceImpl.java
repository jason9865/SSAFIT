package com.ssafit.article.service;

import com.ssafit.article.model.dao.ArticleDao;
import com.ssafit.article.model.dto.request.ArticleModifyDto;
import com.ssafit.article.model.dto.request.ArticleRegistDto;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleDao articleDao;
	private final UserDao userDao;
	
	@Override
	public List<ArticleResponse> getArticleList() {
		return articleDao.selectAll().stream()
				.map(article -> ArticleResponse.from(article,userDao.selectByUserSeq(article.getUserSeq())))
				.collect(toList());
	}


	@Override // 조회 수 증가 O
	public ArticleResponse readArticle(int articleId) {
		Article article = articleDao.selectById(articleId);
		User user = userDao.selectByUserSeq(article.getUserSeq());
		articleDao.updateViewCnt(articleId);
		return ArticleResponse.from(article,user);
	}
	
	@Override // 조회 수 증가 X
	public ArticleResponse getArticle(int articleId) {
		Article article = articleDao.selectById(articleId);
		User user = userDao.selectByUserSeq(article.getUserSeq());
		return ArticleResponse.from(article,user);
	}
	

	@Override
	public boolean writeArticle(ArticleRegistDto request,int userSeq ) {
		User user = userDao.selectByUserSeq(userSeq);
		Article newArticle =
				Article.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.boardId(request.getBoardId())
				.userSeq(userSeq)
				.build();
		return articleDao.insertArticle(newArticle) > 0;
	}

	@Override
	public boolean modifyArticle(ArticleModifyDto request, int articleId, int userSeq) {
		Article article = articleDao.selectById(articleId);
		User user = userDao.selectByUserSeq(article.getUserSeq());
		Article newArticle =
				Article.builder()
						.articleId(articleId)
						.title(request.getTitle())
						.content(request.getContent())
						.boardId(article.getBoardId())
						.userSeq(article.getUserSeq())
						.viewCnt(article.getViewCnt())
						.createdAt(article.getCreatedAt())
						.modifiedAt(article.getModifiedAt())
						.build();

		return articleDao.updateArticle(newArticle) > 0;
	}

	@Override
	public boolean deleteArticle(int articleId) {
		return articleDao.deleteArticle(articleId) > 0;
	}


}
