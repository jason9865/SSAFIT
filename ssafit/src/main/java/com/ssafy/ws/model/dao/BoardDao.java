package com.ssafy.ws.model.dao;

import java.util.List;

import com.ssafy.ws.model.dto.Board;

public interface BoardDao {
	
	List<Board> selectAll();
	
	Board selectOne(int id);
	
	void insertBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(int id);
	
}
