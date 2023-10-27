package com.ssafy.ws.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.ws.model.dto.Article;
import com.ssafy.ws.model.dto.Board;
import com.ssafy.ws.model.service.ArticleService;
import com.ssafy.ws.model.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	private final BoardService boardService;
	private final ArticleService articleService;
	
	@Autowired
	public BoardController(BoardService boardService,ArticleService articleService) {
		this.boardService = boardService;
		this.articleService = articleService;
	}
	
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Board> list = boardService.getList();
		model.addAttribute("list",list);
		return "/mainPage";
	}
	
	@GetMapping("/board/regist")
	public String registBoard() {
		return "/board/registBoard";
	}

	@PostMapping("/board/regist")
	public String regist(@ModelAttribute Board board) {
		boardService.registBoard(board);
		return "redirect:/";
	}
	
	@GetMapping("/board/{id}")
	public String boardDetail(@PathVariable("id") int id, Model model) {
		List<Article> articleList = articleService.getArticleList().stream().
				filter(a -> a.getBoardId() == id).
				collect(Collectors.toList());
		
		model.addAttribute("board",boardService.getBoard(id));
		model.addAttribute("articleList",articleList);
		return "/board/boardDetail";
	}

	@GetMapping("/board/{id}/delete")
	public String deleteBoard(@PathVariable("id") int id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		boardService.deleteBoard(id);
		String referer = request.getHeader("Referer");
		redirectAttributes.addAttribute("referer",referer);

		return"redirect:/admin/board";
	}

}
