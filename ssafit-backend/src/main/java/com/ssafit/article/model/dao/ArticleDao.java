package com.ssafit.article.model.dao;

import com.ssafit.article.model.entity.Article;
import com.ssafit.article.model.entity.ArticleDislike;
import com.ssafit.article.model.entity.ArticleLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
	
	
	// 게시판에 해당하는 게시물 모두 검색 -> list로 보여주기
	List<Article> selectAll();
	
	// 게시판 별 article 검색
	List<Article> selectByBoardId(@Param("boardId")int boardId);
	
	// 게시판 별 article 검색 with paging
	List<Article> selectByBoardIdPaging(@Param("boardId")int boardId,
								  @Param("currentPage")int currentPage,
								  @Param("articlePerPage")int articlePerPage,
								  @Param("offsetParam")int offsetParam
								  );

	// 유저 별 article 검색
	List<Article> selectByUserSeq(int userSeq);

	// article id로 검색
	Article selectById(int articleId);
	
	// article 등록
	int insertArticle(Article article);
	
	// article 수정
	int updateArticle(Article article);
	
	// article 삭제 <- board_id 사용
	int deleteArticle(int id);
	
	// 조회 수 증가
	void updateViewCnt(int articleId);

	// 특정 유저가 누른 좋아요 개수 파악하기
	List<Integer> selectArticleLikeList(int userSeq);

	// 좋아요 개수 확인
	ArticleLike selectArticleLike(@Param("articleId")int articleId, @Param("userSeq")int userSeq);

	// 게시글 별 좋아요 개수 확인
	int selectLikeCount(int articleId);

	// 좋아요 눌렀는지 확인
	int findLike(Map<String, Integer> map);

	// 좋아요 등록
	int insertLike(ArticleLike articleLike);

	// 좋아요 취소
	int deleteLike(Map<String,Integer>map);

	// 게시판 별 싫어요 개수 확인
	int selectDislikeCount(int articleId);

	// 싫어요 개수 확인
	ArticleDislike selectArticleDislike(@Param("articleId")int articleId, @Param("userSeq")int userSeq);

	// 싫어요 눌렀는지 확인
	int findDislike(Map<String, Integer> map);

	// 싫어요 증가(싫어요 등록)
	int insertDislike(ArticleDislike articleDislike);

	// 싫어요 취소
	int deleteDislike(Map<String,Integer>map);

}
