package com.ssafit.board.controller;

import com.ssafit.article.model.dto.Article;
import com.ssafit.board.model.dto.Board;
import com.ssafit.article.service.ArticleService;
import com.ssafit.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags="게시판 컨트롤러")
public class BoardController {
	
	private final BoardService boardService;
	private final ArticleService articleService;
	
	@Autowired
	public BoardController(BoardService boardService, ArticleService articleService) {
		this.boardService = boardService;
		this.articleService = articleService;
	}

	@GetMapping("/board")
	@ApiOperation(value="게시판 리스트", notes="게시판 전부를 보여줍니다.")
	public ResponseEntity<List<Board>> registBoard() {
		List<Board> boardList = boardService.getList();

		return new ResponseEntity<List<Board>>(boardList,HttpStatus.OK);
	}

	@PostMapping("/board/regist")
	@ApiOperation(value="게시판 등록", notes="관리자만 등록이 가능합니다.")
	public ResponseEntity<Boolean> regist(@RequestBody Board board) {
		boolean isRegistered = boardService.registBoard(board);
		if (!isRegistered)
			return new ResponseEntity<Boolean>(isRegistered,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isRegistered,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/board/{id}")
	@ApiOperation(value="게시판 별 게시글", notes="게시판 별 게시글을 보여줍니다.")
	public ResponseEntity<List<Article>> boardDetail(@PathVariable("id") int id) {
		List<Article> articleList = articleService.getArticleList().stream().
				filter(a -> a.getBoardId() == id).
				collect(Collectors.toList());

		return new ResponseEntity<List<Article>>(articleList,HttpStatus.OK);
	}

//	@GetMapping("/board/{id}/delete")
	@DeleteMapping("/board/{id}/delete")
	@ApiOperation(value="게시판 삭제", notes="관리자 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> deleteBoard(@PathVariable("id") int id) {
		boolean isDeleted = boardService.deleteBoard(id);
		if (!isDeleted)
			return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.ACCEPTED);
	}



}
