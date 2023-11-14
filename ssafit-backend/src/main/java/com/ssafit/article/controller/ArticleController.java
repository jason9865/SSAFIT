package com.ssafit.article.controller;

import com.ssafit.article.model.dto.request.ArticleModifyRequest;
import com.ssafit.article.model.dto.request.ArticleRegistRequest;
import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.service.ArticleService;
import com.ssafit.comment.service.CommentService;
import com.ssafit.user.model.dto.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/article")
@Api(tags="게시글 컨트롤러")
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
	public ResponseEntity<List<ArticleResponse>> getAllArticles() {
		List<ArticleResponse> articleList = articleService.getArticleList();
		return new ResponseEntity<List<ArticleResponse>>(articleList,HttpStatus.OK);
	}
	
	@GetMapping("/{articleId}")
	@ApiOperation(value="게시글 조회", notes="게시글과 댓글을 보여줍니다.")
	public ResponseEntity<ArticleResponse> getArticle(@PathVariable int articleId) {

		ArticleResponse articleResponse = articleService.readArticle(articleId);
		return new ResponseEntity<ArticleResponse>(articleResponse, HttpStatus.OK);
	}
	

	@PostMapping("/write")
	@ApiOperation(value="게시글 등록", notes="로그인 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> writeArticle(
			@RequestBody ArticleRegistRequest articleRegistRequest,
			HttpSession session	) {
		System.out.println(articleRegistRequest.getBoardId() + "--------------------");
		System.out.println(session.getAttribute("loginUser"));
		UserResponse loginUser = (UserResponse)session.getAttribute("loginUser");
		if(loginUser == null) System.out.println("tlqkf");
		int userSeq = loginUser.getUserSeq();
		boolean isRegistered = articleService.writeArticle(articleRegistRequest, userSeq);
		if (!isRegistered)
			return new ResponseEntity<Boolean>(isRegistered,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isRegistered,HttpStatus.OK);
	}

	
	@PutMapping("/{articleId}/update")
	@ApiOperation(value="게시글 갱신", notes="관리자 계정과 해당 게시글 작성자만 사용가능합니다.")
	public ResponseEntity<Boolean> updateArticle(
			@PathVariable int articleId,
			@RequestBody ArticleModifyRequest articleModifyRequest,
			HttpSession session) {

		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		int userSeq = loginUser.getUserSeq();
		boolean isUpdated = articleService.modifyArticle(articleModifyRequest, articleId, userSeq);

		if (!isUpdated)
			return new ResponseEntity<Boolean>(isUpdated,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
	}
	
	@DeleteMapping("/{articleId}/delete")
	@ApiOperation(value="게시글 삭제", notes="관리자 계정과 로그인 계정만 사용가능합니다.")
	public ResponseEntity<Boolean> deleteArticle(
			@PathVariable int articleId, HttpSession session) {
		int boardId = articleService.readArticle(articleId).getBoardId();
		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		int userSeq = loginUser.getUserSeq();

		boolean isDeleted = articleService.deleteArticle(articleId);
		if (!isDeleted)
			return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);

	}

	@GetMapping("/{articleId}/like")
	@ApiOperation(value="게시글 별 좋아요 개수")
	public ResponseEntity<Integer>getLikeNumber(@PathVariable int articleId) {
		return new ResponseEntity<Integer>(articleService.getLikeCount(articleId),HttpStatus.OK);
	}

	@PostMapping("/{articleId}/like")
	@ApiOperation(value="게시글 좋아요 누르기", notes="게시글에 좋아요를 누릅니다.")
	public ResponseEntity<Boolean> doArticleLike(@PathVariable int articleId, HttpSession session){
		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		int userSeq = loginUser.getUserSeq();
		boolean isLikeAdded = articleService.addArticleLike(articleId, userSeq);
		if (!isLikeAdded)
			return new ResponseEntity<Boolean>(isLikeAdded,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isLikeAdded,HttpStatus.OK);
	}

	@DeleteMapping("/{articleLikeId}/like")
	@ApiOperation(value="게시글 좋아요 취소", notes="게시글에 좋아요를 취소합니다.")
	public ResponseEntity<Boolean> undoArticleLike(@PathVariable int articleLikeId, HttpSession session){
		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		int userSeq = loginUser.getUserSeq();
		boolean isDislikeUndone = articleService.deleteArticleLike(articleLikeId);
		if (!isDislikeUndone)
			return new ResponseEntity<Boolean>(isDislikeUndone,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isDislikeUndone,HttpStatus.OK);
	}

	@GetMapping("/{articleId}/dislike")
	@ApiOperation(value="게시글 별 싫어요 개수")
	public ResponseEntity<Integer>getDislikeNumber(@PathVariable int articleId) {
		return new ResponseEntity<Integer>(articleService.getDislikeCount(articleId),HttpStatus.OK);
	}

	@PostMapping("/{articleId}/dislike")
	@ApiOperation(value="게시글 싫어요 누르기", notes="게시글에 싫어요를 누릅니다.")
	public ResponseEntity<Boolean> doArticleDisLike(@PathVariable int articleId, HttpSession session){
		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		int userSeq = loginUser.getUserSeq();
		boolean isDislikeAdded = articleService.addArticleDislike(articleId, userSeq);
		if (!isDislikeAdded)
			return new ResponseEntity<Boolean>(isDislikeAdded,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isDislikeAdded,HttpStatus.OK);
	}

	@DeleteMapping("/{articleDisLikeId}/dislike")
	@ApiOperation(value="게시글 싫어요 취소", notes="게시글에 싫어요를 취소합니다.")
	public ResponseEntity<Boolean> undoArticleDislike(@PathVariable int articleDisLikeId, HttpSession session){
		UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
		int userSeq = loginUser.getUserSeq();
		boolean isDislikeUndone = articleService.deleteArticleDislike(articleDisLikeId);
		if (!isDislikeUndone)
			return new ResponseEntity<Boolean>(isDislikeUndone,HttpStatus.NO_CONTENT);
		return new ResponseEntity<Boolean>(isDislikeUndone,HttpStatus.OK);
	}





}
