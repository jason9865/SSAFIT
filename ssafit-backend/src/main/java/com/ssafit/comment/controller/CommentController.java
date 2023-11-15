package com.ssafit.comment.controller;

import com.ssafit.comment.model.dto.response.CommentResponse;
import com.ssafit.comment.model.dto.resquest.CommentModifyRequest;
import com.ssafit.comment.model.dto.resquest.CommentRegistRequest;
import com.ssafit.comment.model.entity.Comment;
import com.ssafit.comment.service.CommentService;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(tags="댓글(리뷰) 컨트롤러")
@CrossOrigin("http://localhost:5173/")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value="댓글 리스트 조회", notes="모든 댓글을 보여줍니다.")
    public ResponseEntity<?> showCommentsByArticle(@PathVariable int articleId){
        List<CommentResponse> commentList = commentService.getList(articleId);
        return new ResponseEntity<List<CommentResponse>>(commentList,HttpStatus.OK);
    }

    @GetMapping("/detail/{commentId}")
    @ApiOperation(value="댓글 1개 조회", notes="댓글 1개를 보여줍니다.")
    public ResponseEntity<CommentResponse> commentDetail(@PathVariable int commentId) {
        return new ResponseEntity<CommentResponse>(commentService.getComment(commentId),HttpStatus.OK);
    }

    @PostMapping("/{articleId}")
    @ApiOperation(value="댓글 작성", notes="로그인한 사용자만 댓글작성이 가능합니다.")
    public ResponseEntity<Boolean> writeComment(
            @RequestBody CommentRegistRequest commentRegistRequest,
            @PathVariable int articleId,
            HttpSession session){

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
        int userSeq = loginUser.getUserSeq();
        boolean isWritten = commentService.writeComment(commentRegistRequest,articleId, userSeq);
        if (!isWritten)
            return new ResponseEntity<Boolean>(isWritten,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isWritten,HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    @ApiOperation(value="댓글 수정", notes="해당 댓글 작성만 수정이 가능합니다.")
    public ResponseEntity<Boolean> modifyComment(
            @RequestBody CommentModifyRequest commentModifyRequest,
            @PathVariable int commentId,
            HttpSession session
            ) {
        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
        int userSeq = loginUser.getUserSeq();
        boolean isModified = commentService.modifyComment(commentModifyRequest, commentId, userSeq);

        if(!isModified)
            return new ResponseEntity<Boolean>(isModified,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isModified,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation(value="댓글 삭제", notes="로그인 유저만 사용 가능합니다.")
    public ResponseEntity<Boolean> removeComment(@PathVariable int commentId) {
        boolean isModified = commentService.removeComment(commentId);
        if (!isModified)
            return new ResponseEntity<Boolean>(isModified,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isModified,HttpStatus.OK);
    }

    @GetMapping("/{commentId}/like")
    @ApiOperation(value="댓글 별 좋아요 개수")
    public ResponseEntity<Integer> getLikeNumber(@PathVariable int commentId) {
        return new ResponseEntity<Integer>(commentService.getLikeCount(commentId),HttpStatus.OK);
    }

    @PostMapping("/{commentId}/like")
    @ApiOperation(value = "댓글 좋아요 누르기")
    public ResponseEntity<Boolean> doCommentLike(@PathVariable int commentId, HttpServletRequest request) {
//        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
//        int userSeq = loginUser.getUserSeq();
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));

        boolean isLikeDone = commentService.addCommentLike(commentId,userSeq);
        if (!isLikeDone)
            return new ResponseEntity<Boolean>(isLikeDone,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isLikeDone,HttpStatus.OK);
    }

    @DeleteMapping("/{commentLikeId}/like")
    @ApiOperation(value = "댓글 좋아요 취소")
    public ResponseEntity<Boolean> undoCommentLike(@PathVariable int commentLikeId) {
        boolean isLikedUndone = commentService.deleteCommentLike(commentLikeId);
        if (!isLikedUndone)
            return new ResponseEntity<Boolean>(isLikedUndone,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isLikedUndone,HttpStatus.OK);
    }

    @GetMapping("/{commentId}/dislike")
    @ApiOperation(value="댓글 별 싫어요 개수")
    public ResponseEntity<Integer> getDislikeNumber(@PathVariable int commentId) {
        return new ResponseEntity<Integer>(commentService.getDislikeCount(commentId),HttpStatus.OK);
    }

    @PostMapping("/{commentId}/dislike")
    @ApiOperation(value = "댓글 싫어요 누르기")
    public ResponseEntity<Boolean> doCommentDislike(@PathVariable int commentId, HttpServletRequest request) {
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));

        boolean isDislikeDone = commentService.addCommentDislike(commentId,userSeq);
        if (!isDislikeDone)
            return new ResponseEntity<Boolean>(isDislikeDone,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isDislikeDone,HttpStatus.OK);
    }

    @DeleteMapping("/{commentDislikeId}/dislike")
    @ApiOperation(value = "댓글 싫어요 취소")
    public ResponseEntity<Boolean> undoCommentDislike(@PathVariable int commentDislikeId) {
        boolean isDislikeUndone = commentService.deleteCommentDislike(commentDislikeId);
        if (!isDislikeUndone)
            return new ResponseEntity<Boolean>(isDislikeUndone,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isDislikeUndone,HttpStatus.OK);
    }

}
