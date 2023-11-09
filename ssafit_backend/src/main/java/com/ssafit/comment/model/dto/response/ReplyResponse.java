package com.ssafit.comment.model.dto.response;

import com.ssafit.comment.model.entity.Reply;
import com.ssafit.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public static ReplyResponse from(Reply reply, User user) {
        return new ReplyResponse(
                reply.getReplyId(),
                reply.getContent(),
                reply.getCreatedAt(),
                reply.getUserSeq(),
                user.getUserId(),
                user.getNickName());
    }

//    @Builder
//    public ReplyResponse(Reply reply, User user){
//        this.replyId = reply.getReplyId();
//        this.content = reply.getContent();
//        this.createdAt = reply.getCreatedAt();
//        this.userSeq = reply.getUserSeq();
//        this.userId = user.getUserId();
//        this.nickName = user.getNickName();
//    }
}
