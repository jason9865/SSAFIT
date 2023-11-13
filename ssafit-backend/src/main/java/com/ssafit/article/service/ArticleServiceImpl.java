package com.ssafit.article.service;

import com.ssafit.article.model.dao.ArticleDao;
import com.ssafit.article.model.dto.request.ArticleModifyRequest;
import com.ssafit.article.model.dto.request.ArticleRegistRequest;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;
import com.ssafit.article.model.entity.ArticleDislike;
import com.ssafit.article.model.entity.ArticleLike;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public boolean writeArticle(ArticleRegistRequest request, int userSeq ) {
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
	public boolean modifyArticle(ArticleModifyRequest request, int articleId, int userSeq) {
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


	// 좋아요
	@Override
	public int getLikeCount(int articleId) {
		return articleDao.selectLikeCount(articleId);
	}
	
	@Override
	public boolean addArticleLike(int articleId, int userSeq) {
		if (!isAvailable(articleId,userSeq)){
			System.out.println("좋아요 안 돼. 안 해줘. 돌아가.");
			return false;
		}
		
		ArticleLike articleLike =
				ArticleLike.builder()
						.articleId(articleId)
						.userSeq(userSeq)
						.build();
		
		return articleDao.insertLike(articleLike) > 0;
	}

	@Override
	public boolean deleteArticleLike(int articleLikeId) {
		return articleDao.deleteLike(articleLikeId) > 0;
	}
	
	// 싫어요

	@Override
	public int getDislikeCount(int articleId){
		return articleDao.selectDislikeCount(articleId);
	}

	@Override
	public boolean addArticleDislike(int articleId, int userSeq) {
		if (!isAvailable(articleId,userSeq)){
			System.out.println("싫어요 안 돼. 안 해줘. 돌아가.");
			return false;
		}

		ArticleDislike articleDislike =
				ArticleDislike.builder()
						.articleId(articleId)
						.userSeq(userSeq)
						.build();


		return articleDao.insertDislike(articleDislike) > 0;
	}


	@Override
	public boolean deleteArticleDislike(int articleDislikeId) {
		return articleDao.deleteDislike(articleDislikeId) > 0;
	}

	@Override
	public ArticleLike findArticleLike(int articleId, int userSeq) {
		return articleDao
				.selectArticleLike(articleId, userSeq);
	}

	@Override
	public ArticleDislike findArticleDislike(int articleId, int userSeq) {
		return articleDao.selectArticleDislike(articleId, userSeq);
	}

	@Override
	public 	boolean isAvailable(int articleId, int userSeq){
		return findArticleLike(articleId, userSeq) == null && findArticleDislike(articleId,userSeq) == null;
	}


}
