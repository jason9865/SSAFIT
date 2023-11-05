package com.ssafit.comment.controller;


import com.ssafit.comment.model.dto.response.ReplyResponse;
import com.ssafit.comment.model.entity.Reply;
import com.ssafit.comment.model.dto.resquest.ReplyRegisterRequest;
import com.ssafit.comment.model.dto.resquest.ReplyModifyRequest;
import com.ssafit.comment.service.ReplyService;
import com.ssafit.user.model.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/reply")
@Api(tags="대댓글 컨트롤러")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<Boolean> registerReply(
            @RequestBody ReplyRegisterRequest replyRegisterRequest,
            @PathVariable final int commentId,
            HttpSession session
            ) {
        User loginUser = (User)session.getAttribute("loginUser");
        int userSeq = loginUser.getUserSeq();
        boolean isRegistered = replyService.writeReply(replyRegisterRequest, commentId, userSeq);
        if (!isRegistered)
            return new ResponseEntity<Boolean>(isRegistered, HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isRegistered,HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getReplyList(@PathVariable final int commentId){
        return new ResponseEntity<List<ReplyResponse>>(replyService.getReplyByComment(commentId),HttpStatus.OK);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity<Boolean> modifyReply(
            @PathVariable final int replyId,
            @RequestBody final ReplyModifyRequest replyModifyRequest,
            final int userSeq) {
        boolean isModified = replyService.modifyReply(replyModifyRequest,replyId,userSeq);
        if (!isModified)
            return new ResponseEntity<Boolean>(isModified,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isModified, HttpStatus.OK);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<Boolean> deleteReply(
            @PathVariable final int replyId,
            final int userSeq){
        boolean isDeleted = replyService.removeReply(replyId, userSeq);
        if (!isDeleted)
            return new ResponseEntity<Boolean>(isDeleted,HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
    }


}
