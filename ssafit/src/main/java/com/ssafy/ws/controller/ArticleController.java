package com.ssafy.ws.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssafy.ws.model.dto.Comment;
import com.ssafy.ws.model.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.ws.model.dto.Article;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.ArticleService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	private final ArticleService articleService;
	private final CommentService commentService;
	
	@Autowired
	public ArticleController(ArticleService articleService, CommentService commentService) {
		this.articleService = articleService;
		this.commentService = commentService;
	}
	
	@GetMapping("/{articleId}")
	public String showArticle(@PathVariable int articleId, Model model) {
		Article article = articleService.ReadArticle(articleId);
		List<Comment> commentList = commentService.getList(articleId);
		model.addAttribute(article);
		model.addAttribute(commentList);
		return "/article/articleDetail";
	}
	
	@GetMapping("/regist")
	public String registArticlePage(@RequestParam int boardId, Model model) {
		model.addAttribute("boardId",boardId);
		return "/article/registArticle";
	}
	
	@PostMapping("/regist")
	public String registArticle(@RequestParam int boardId, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		User loginUser = (User)session.getAttribute("loginUser");
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		article.setBoardId(boardId);
		article.setUserSeq(loginUser.getUserSeq());
		articleService.writeArticle(article);
		
		redirectAttributes.addAttribute("boardId",boardId);
		return "redirect:/board/{boardId}"; // 게시판 main화면으로 이동
	}
	
	@GetMapping("/{articleId}/update")
	public String updateArticlePage(@PathVariable int articleId, Model model) {
		model.addAttribute("article",articleService.searchArticleById(articleId));
		return "/article/updateArticle";
	}
	
	@PostMapping("/{articleId}/update")
	public String updateArticle(@PathVariable int articleId,
								HttpServletRequest request, Model model,RedirectAttributes redirectAttributes) {
		
		Article article = articleService.searchArticleById(articleId);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if (title == null || content == null) {
			model.addAttribute("msg","제목이나 내용이 비어서는 안됩니다.");
			return "/article/" + articleId + "/update";
		}
		
		article.setTitle(title);
		article.setContent(content);
		
		articleService.modifyArticle(article);
		int boardId = article.getBoardId();
		
		redirectAttributes.addAttribute("articleId", articleId);
	
		return "redirect:/article/{articleId}";
	}
	
	@GetMapping("/{articleId}/delete")
	public String deleteArticle(@PathVariable int articleId,RedirectAttributes redirectAttributes, HttpSession session) {
		int boardId = articleService.searchArticleById(articleId).getBoardId();
		User loginUser = (User)session.getAttribute("loginUser");

		articleService.deleteArticle(articleId);

		if (loginUser.getUserRank() == 2)
			return "redirect:/admin/article";

		redirectAttributes.addAttribute("boardId", boardId);
		return "redirect:/board/{boardId}";
	
	}



}
