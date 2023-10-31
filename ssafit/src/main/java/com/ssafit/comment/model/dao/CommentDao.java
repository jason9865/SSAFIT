package com.ssafit.comment.model.dao;

import com.ssafit.comment.model.dto.Comment;

import java.util.List;

public interface CommentDao {
	
	List<Comment> selectAll();
	
	Comment selectOne(int commentId);
	
	int insertComment(Comment comment);
	
	int updateComment(Comment comment);
	
	int deleteComment(int commentId);
	
	void addLike(int commentId);
	
	void addDislike(int commentId);
	
	int selectLike(int commentId);
	
	int selectDislike(int commentId);
}
