package com.ssafit.article.service;

import com.ssafit.article.model.dao.ArticleDao;
import com.ssafit.article.model.dto.request.ArticleModifyRequest;
import com.ssafit.article.model.dto.request.ArticleRegistRequest;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;
import com.ssafit.article.model.entity.ArticleDislike;
import com.ssafit.article.model.entity.ArticleLike;
import com.ssafit.board.model.dao.BoardDao;
import com.ssafit.board.model.entity.Board;
import com.ssafit.board.model.entity.SearchCondition;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.entity.User;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleDao articleDao;
	private final UserDao userDao;
	private final BoardDao boardDao;
	private final int articlePerPage = 10;

	@Override
	public List<ArticleResponse> getArticleList() {
		return articleDao.selectAll().stream()
				.map(article ->
						ArticleResponse.from(
								article,
								userDao.selectByUserSeq(article.getUserSeq()),
								boardDao.selectOne(article.getBoardId())
						))
				.collect(toList());
	}

	@Override
	public List<ArticleResponse> getArticleList(int boardId) {
		return articleDao.selectByBoardId(boardId).stream()
				.map(article ->
						ArticleResponse.from(
								article,
								userDao.selectByUserSeq(article.getUserSeq()),
								boardDao.selectOne(article.getBoardId())
						))
				.collect(toList());
	}

	@Override
	public List<ArticleResponse> getArticleList(int boardId, int currentPage) {
		int offsetParam = ((currentPage) - 1) * articlePerPage;
		return articleDao.selectByBoardIdPaging(boardId, currentPage, articlePerPage, offsetParam).stream()
				.map(article ->
						ArticleResponse.from(
								article,
								userDao.selectByUserSeq(article.getUserSeq()),
								boardDao.selectOne(article.getBoardId())
						))
				.collect(toList());
	}
	
	@Override
	public List<ArticleResponse> searchArticles(int boardId, SearchCondition condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId", boardId);
		map.put("key", condition.getKey());
		map.put("word", condition.getWord());
		map.put("orderBy", condition.getOrderBy());
		map.put("orderBydir", condition.getOrderByDir());
		return articleDao.searchArticles(map).stream()
				.map(article ->
				ArticleResponse.from(
						article,
						userDao.selectByUserSeq(article.getUserSeq()),
						boardDao.selectOne(article.getBoardId())
						))
				.collect(toList());
	}
	
	@Override
	public List<ArticleResponse> searchArticles(int boardId, int currentPage, SearchCondition condition) {
		int offsetParam = ((currentPage) - 1) * articlePerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardId", boardId);
		map.put("key", condition.getKey());
		map.put("word", condition.getWord());
		map.put("orderBy", condition.getOrderBy());
		map.put("orderBydir", condition.getOrderByDir());
		map.put("currentPage", currentPage);
		map.put("articlePerPage", articlePerPage);
		map.put("offsetParam", offsetParam);
		return articleDao.searchArticlesPaging(map).stream()
				.map(article ->
				ArticleResponse.from(
						article,
						userDao.selectByUserSeq(article.getUserSeq()),
						boardDao.selectOne(article.getBoardId())
						))
				.collect(toList());
	}
	
	@Override
	public List<ArticleResponse> getArticleListByUser(int userSeq) {
		return articleDao.selectByUserSeq(userSeq).stream()
				.map(article ->
						ArticleResponse.from(
								article,
								userDao.selectByUserSeq(userSeq),
								boardDao.selectOne(article.getBoardId())
						))
				.collect(toList());

	}


	@Override
	public List<ArticleResponse> getArticleLikeList(int userSeq) {
		return articleDao.selectArticleLikeList(userSeq).stream()
				.map(articleId -> ArticleResponse.from(
						articleDao.selectById(articleId),
						userDao.selectByUserSeq(userSeq),
						boardDao.selectOne(articleDao.selectById(articleId).getBoardId())
				))
				.collect(toList());
	}

	@Override // 조회 수 증가 O
	public ArticleResponse readArticle(int articleId) {
		Article article = articleDao.selectById(articleId);
		User user = userDao.selectByUserSeq(article.getUserSeq());
		Board board = boardDao.selectOne(article.getBoardId());
		articleDao.updateViewCnt(articleId);
		return ArticleResponse.from(article,user,board);
	}

	@Override // 조회 수 증가 X
	public ArticleResponse getArticle(int articleId) {
		Article article = articleDao.selectById(articleId);
		User user = userDao.selectByUserSeq(article.getUserSeq());
		Board board = boardDao.selectOne(article.getBoardId());
		return ArticleResponse.from(article,user,board);
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
	public int isLiked(int articleId, int userSeq) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("articleId",articleId);
		map.put("userSeq",userSeq);
		return articleDao.findLike(map);
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
	public boolean deleteArticleLike(int articleId, int userSeq) {
		Map<String,Integer>map = new HashMap<String, Integer>();
		map.put("articleId",articleId);
		map.put("userSeq",userSeq);
		return articleDao.deleteLike(map) > 0;
	}

	// 싫어요

	@Override
	public int getDislikeCount(int articleId){
		return articleDao.selectDislikeCount(articleId);
	}

	@Override
	public int isDisliked(int articleId, int userSeq){
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("articleId",articleId);
		map.put("userSeq",userSeq);
		return articleDao.findDislike(map);
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
	public boolean deleteArticleDislike(int articleId, int userSeq) {
		Map<String,Integer>map = new HashMap<String,Integer>();
		map.put("articleId",articleId);
		map.put("userSeq",userSeq);
		return articleDao.deleteDislike(map) > 0;
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
