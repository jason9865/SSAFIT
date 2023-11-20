package com.ssafit.article.service;

import com.ssafit.article.model.dto.request.ArticleModifyRequest;
import com.ssafit.article.model.dto.request.ArticleRegistRequest;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.ArticleDislike;
import com.ssafit.article.model.entity.ArticleLike;
import com.ssafit.board.model.entity.Board;
import com.ssafit.board.model.entity.SearchCondition;

import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;

public interface ArticleService {
	
	List<ArticleResponse> getArticleList();
	
	List<ArticleResponse> getArticleList(int boardId);
	
	List<ArticleResponse> getArticleList(int boardId, int currentPage);

	List<ArticleResponse> getArticleListByUser(int userSeq);

	List<ArticleResponse> getArticleLikeList(int userSeq);
	
	List<ArticleResponse> searchArticles(int boardId, SearchCondition condition);
	
	List<ArticleResponse> searchArticles(int boardId, int currentPage, SearchCondition condition);

	ArticleResponse getArticle(int articleId);
	
	ArticleResponse readArticle(int articleId);

	boolean writeArticle(ArticleRegistRequest articleRegistRequest, int userSeq);

	boolean modifyArticle(ArticleModifyRequest articleModifyRequest, int articleId, int userSeq);

	boolean deleteArticle(int articleId);

	int getLikeCount(int articleId);

	int isLiked(int articleId, int userSeq);

	boolean addArticleLike(int articleId, int userSeq);

	boolean deleteArticleLike(int articleId, int userSeq);

	int getDislikeCount(int articleId);

	int isDisliked(int articleId, int userSeq);

	boolean addArticleDislike(int articleId, int userSeq);

	boolean deleteArticleDislike(int articleId, int userSeq);

	ArticleLike findArticleLike(int articleId, int userSeq);

	ArticleDislike findArticleDislike(int articleId, int userSeq);

	boolean isAvailable(int articleId, int userSeq);

}
