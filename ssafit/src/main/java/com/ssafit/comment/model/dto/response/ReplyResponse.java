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
    private int userSeq;
    private String content;
    private String createdAt;



    public static ReplyResponse from(final Reply reply) {
        return new ReplyResponse(
                reply.getReplyId(),
                reply.getUserSeq(),
                reply.getContent(),
                reply.getCreatedAt()
        );
    }

}
