package com.ssafit.comment.controller;


import com.ssafit.comment.model.dto.LoginUser;
import com.ssafit.comment.model.dto.Reply;
import com.ssafit.comment.model.dto.ReplyRegisterRequest;
import com.ssafit.comment.model.dto.ReplyModifyRequest;
import com.ssafit.comment.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListResourceBundle;


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
            int userSeq // 추후 @LoginUser로 대체할 예정(Session에서 가져옴)
            ) {
        boolean isRegistered = replyService.writeReply(replyRegisterRequest, commentId, userSeq);
        if (!isRegistered)
            return new ResponseEntity<Boolean>(isRegistered, HttpStatus.NO_CONTENT);
        return new ResponseEntity<Boolean>(isRegistered,HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getReplyList(@PathVariable final int commentId){
        return new ResponseEntity<List<Reply>>(replyService.getReplyByComment(commentId),HttpStatus.OK);
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

}
