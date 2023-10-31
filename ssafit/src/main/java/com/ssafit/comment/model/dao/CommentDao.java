package com.ssafit.comment.model.dao;

import com.ssafit.comment.model.dto.Comment;

import java.util.List;

public interface CommentDao {
	
	List<Comment> selectAll();
	
	Comment selectOne(int id);
	
	int insertComment(Comment comment);
	
	int updateComment(Comment comment);
	
	int deleteComment(int id);
	
	void addLike(int id);
	
	void addDislike(int id);
	
	int selectLike(int id);
	
	int selectDislike(int id);
}
