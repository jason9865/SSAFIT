package com.ssafy.ws.model.dao;

import java.util.List;

import com.ssafy.ws.model.dto.Comment;

public interface CommentDao {
	
	List<Comment> selectAll();
	
	Comment selectOne(int id);
	
	void insertComment(Comment comment);
	
	void updateComment(Comment comment);
	
	void deleteComment(int id);
	
	void addLike(int id);
	
	void addDislike(int id);
	
	int selectLike(int id);
	
	int selectDislike(int id);
}
