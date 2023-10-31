package com.ssafit.article.controller;

import com.ssafit.article.model.dto.Article;
import com.ssafit.article.service.ArticleService;
import com.ssafit.comment.model.dto.Comment;
import com.ssafit.comment.service.CommentService;
import com.ssafit.user.model.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/article")
@Api(tags="게시물 컨트롤러")
public class ArticleController {
	
	private final ArticleService articleService;
	private final CommentService commentService;

	@Autowired
	public ArticleController(ArticleService articleService, CommentService commentService) {
		this.articleService = articleService;
		this.commentService = commentService;
	}

	@GetMapping
	@ApiOperation(value="게시글 전체 조회", notes="모든 게시글을 보여줍니다.")
	public ResponseEntity<List<Article>> showAllArticles() {
		List<Article> articleList = articleService.getArticleList();
		return new ResponseEntity<List<Article>>(articleList,HttpStatus.OK);
	}
	
	@GetMapping("/{articleId}")
	@ApiOperation(value="게시글 조회", notes="게시글과 댓글을 보여줍니다.")
	public ResponseEntity<Map<String,Object>> showArticle(@PathVariable int articleId) {
		Map<String,Object> map = new HashMap<>();

		Article article = articleService.readArticle(articleId);
		List<Comment> commentList = commentService.getList(articleId);
		map.put("article",article);
		map.put("commentList",commentList);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	

	

	@PostMapping("/regist")
	@ApiOperation(value="게시글 등록", notes="로그인 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> registArticle(@RequestBody Article article) {
		boolean isRegistered = articleService.writeArticle(article);
		if (!isRegistered)
			return new ResponseEntity<Boolean>(isRegistered,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isRegistered,HttpStatus.OK);
	}

	
	@PutMapping("/{articleId}/update")
	@ApiOperation(value="게시글 갱신", notes="관리자 계정과 해당 게시글 작성자만 사용가능합니다.")
	public ResponseEntity<Boolean> updateArticle(@PathVariable int articleId, @RequestBody Article article) {

		Article newArticle = articleService.searchArticleById(articleId);

		newArticle.setTitle(article.getTitle());
		newArticle.setContent(article.getContent());
		
		boolean isUpdated = articleService.modifyArticle(newArticle);

		if (!isUpdated)
			return new ResponseEntity<Boolean>(isUpdated,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
	}
	
//	@GetMapping("/{articleId}/delete")
	@DeleteMapping("/{articleId}/delete")
	@ApiOperation(value="게시글 삭제", notes="관리자 계정과 로그인 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> deleteArticle(@PathVariable int articleId,RedirectAttributes redirectAttributes, HttpSession session) {
		int boardId = articleService.searchArticleById(articleId).getBoardId();
		User loginUser = (User)session.getAttribute("loginUser");

		boolean isDeleted = articleService.deleteArticle(articleId);
		if (!isDeleted)
			return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);

	}



}
