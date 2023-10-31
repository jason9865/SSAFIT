package com.ssafit.article.model.dao;

import com.ssafit.article.model.dto.Article;

import java.util.List;

public interface ArticleDao {
	// 게시판에 해당하는 게시물 모두 검색 -> list로 보여주기
	List<Article> selectAll();
		
	// 게시판 별 article 검색

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
	
	// 좋아요 증가(좋아요 등록)
	void addLike();
	
	// 싫어요 증가(싫어요 등록)
	void addDislike();
}
