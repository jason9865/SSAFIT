package com.ssafit.board.service;

import com.ssafit.board.model.dao.BoardDao;
import com.ssafit.board.model.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;
	
	@Autowired
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public List<Board> getList() {
		return boardDao.selectAll();
	}

	@Override
	public Board getBoard(int id) {
		return boardDao.selectOne(id);
	}

	@Override
	public boolean registBoard(Board board) {
		return boardDao.insertBoard(board) > 0;
	}

	@Override
	public boolean modifyBoard(Board board) {
		return boardDao.updateBoard(board) > 0;
	}

	@Override
	public boolean deleteBoard(int id) {
		return boardDao.deleteBoard(id) > 0;
	}

}
