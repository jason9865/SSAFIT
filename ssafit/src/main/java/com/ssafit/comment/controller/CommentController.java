package com.ssafit.comment.controller;

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

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<Boolean> registComment(
            @RequestBody CommentRegistRequest commentRegistRequest,
            @PathVariable int articleId,
            HttpSession session){

        UserResponse loginUser = (UserResponse) session.getAttribute("loginUser");
        int userSeq = loginUser.getUserSeq();
        boolean isRegisted = commentService.writeComment(commentRegistRequest,articleId, userSeq);
        if (!isRegisted)
            return new ResponseEntity<Boolean>(isRegisted,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isRegisted,HttpStatus.OK);
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
        boolean isDeleted = commentService.removeComment(commentId);
        if (!isDeleted)
            return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
    }

}
