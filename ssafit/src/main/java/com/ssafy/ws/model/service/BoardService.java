package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.Board;

public interface BoardService {
	
	List<Board> getList();
	
	Board getBoard(int id);
	
	void registBoard(Board board);
	
	void modifyBoard(Board board);
	
	void deleteBoard(int id);
	
}
