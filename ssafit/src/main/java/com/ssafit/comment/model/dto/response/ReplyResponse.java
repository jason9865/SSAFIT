package com.ssafit.comment.model.dto.response;

import com.ssafit.comment.model.entity.Reply;
import com.ssafit.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponse {
    private int replyId;
    private String content;
    private String createdAt;
    private int userSeq;
    private String userId;
    private String nickName;

    public ReplyResponse(Reply reply, User user) {
        this.replyId = reply.getReplyId();
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAt();
        this.userSeq = reply.getUserSeq();
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
    }
}
