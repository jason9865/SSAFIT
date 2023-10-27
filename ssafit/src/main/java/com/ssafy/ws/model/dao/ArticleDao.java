package com.ssafy.ws.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ws.model.dto.Article;

public interface ArticleDao {
	// 게시판에 해당하는 게시물 모두 검색 -> list로 보여주기
	List<Article> selectAll();
		
	// 게시판 별 article 검색

	// article id로 검색
	Article selectById(int articleId);
	
	// article 등록
	void insertArticle(Article article);
	
	// article 수정
	void updateArticle(Article article);
	
	// article 삭제 <- board_id 사용
	void deleteArticle(int id);
	
	// 조회 수 증가
	void updateViewCnt(int id);
	
	// 좋아요 증가(좋아요 등록)
	void addLike();
	
	// 싫어요 증가(싫어요 등록)
	void addDislike();
}
