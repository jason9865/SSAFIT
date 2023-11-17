package com.ssafit.board.controller;

import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;
import com.ssafit.board.model.entity.Board;
import com.ssafit.article.service.ArticleService;
import com.ssafit.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/board")
@Api(tags="게시판 컨트롤러")
@CrossOrigin("http://localhost:5173/")
public class BoardController {
	
	private final BoardService boardService;
	private final ArticleService articleService;
	
	@Autowired
	public BoardController(BoardService boardService, ArticleService articleService) {
		this.boardService = boardService;
		this.articleService = articleService;
	}


	@GetMapping
	@ApiOperation(value="게시판 리스트", notes="게시판 전부를 보여줍니다.")
	public ResponseEntity<List<Board>> registBoard() {
		List<Board> boardList = boardService.getList();

		return new ResponseEntity<List<Board>>(boardList,HttpStatus.OK);
	}

	@GetMapping("/{boardId}")
	@ApiOperation(value="게시판 별 게시글", notes="게시판 별 게시글을 보여줍니다. 헤더에 페이지 정보를 읽어 ")
	public ResponseEntity<List<ArticleResponse>> boardDetail(@PathVariable int boardId, HttpServletRequest request) {
		List<ArticleResponse> articleList = new ArrayList<>();
		
		if(request.getHeader("currentPage")==null) {
			articleList = articleService.getArticleList(boardId).stream().
					filter(a -> a.getBoardId() == boardId).
					collect(Collectors.toList());
		} else {
			int currentPage = Integer.parseInt(request.getHeader("currentPage"));
			articleList = articleService.getArticleList(boardId, currentPage).stream().
					filter(a -> a.getBoardId() == boardId).
					collect(Collectors.toList());
		}

		return new ResponseEntity<List<ArticleResponse>>(articleList,HttpStatus.OK);
	}

	@GetMapping("/{boardId}/detail")
	@ApiOperation(value="게시판 1개 가져오기")
	public ResponseEntity<Board> getBoard(@PathVariable int boardId){
		Board board = boardService.getBoard(boardId);
		return new ResponseEntity<Board>(board,HttpStatus.OK);
	}

	@PostMapping("/write")
	@ApiOperation(value="게시판 등록", notes="관리자만 등록이 가능합니다.")
	public ResponseEntity<Boolean> regist(@RequestBody Board board) {
		boolean isRegistered = boardService.registBoard(board);
		if (!isRegistered)
			return new ResponseEntity<Boolean>(isRegistered,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isRegistered,HttpStatus.ACCEPTED);
	}

	@PutMapping("/{boardId}/update")
	@ApiOperation(value="게시판 수정", notes="관리자 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> modifyBoard(@RequestBody Board board, @PathVariable int boardId){
		boolean isModified = boardService.modifyBoard(board, boardId);
		if(!isModified)
			return new ResponseEntity<Boolean>(isModified,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isModified,HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{boardId}/delete")
	@ApiOperation(value="게시판 삭제", notes="관리자 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> removeBoard(@PathVariable int boardId) {
		boolean isRemoved = boardService.deleteBoard(boardId);
		if (!isRemoved)
			return new ResponseEntity<Boolean>(isRemoved,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isRemoved,HttpStatus.ACCEPTED);
	}



}
