package com.ssafit.comment.controller;

import com.ssafit.comment.model.entity.Comment;
import com.ssafit.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(tags="댓글(리뷰) 컨트롤러")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value="댓글 리스트 조회", notes="모든 댓글을 보여줍니다.")
    public ResponseEntity<?> showCommentsById(@PathVariable int articleId){
        List<Comment> commentList = commentService.getList(articleId);
        return new ResponseEntity<List<Comment>>(commentList,HttpStatus.OK);
    }

    @PostMapping("/{articleId}")
    @ApiOperation(value="댓글 작성", notes="로그인한 사용자만 댓글작성이 가능합니다.")
    public ResponseEntity<Boolean> registerComments(@PathVariable int articleId, @RequestBody Comment comment){

        boolean isWritten = commentService.writeComment(comment);
        if (!isWritten)
            return new ResponseEntity<Boolean>(isWritten,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isWritten,HttpStatus.OK);
    }

//    @GetMapping("/remove/{commentId}")
    @DeleteMapping("/{commentId}")
    @ApiOperation(value="댓글 삭제", notes="로그인 유저만 사용 가능합니다.")
    public ResponseEntity<Boolean> deleteComment(@PathVariable int articleId,@PathVariable int commentId) {
        boolean isDeleted = commentService.removeComment(commentId);
        if (!isDeleted)
            return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
    }

}
