package com.ssafit.board.model.dao;

import com.ssafit.board.model.dto.Board;

import java.util.List;

public interface BoardDao {
	
	List<Board> selectAll();
	
	Board selectOne(int id);
	
	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(int id);
	
}
