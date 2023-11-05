package com.ssafit.comment.model.dto.response;

import com.ssafit.comment.model.entity.Reply;

public class ReplyResponse {
    private int replyId;
    private int userSeq;
    private String content;
    private String createdAt;

    public ReplyResponse(){

    }

    public ReplyResponse(int replyId, int userSeq, String content, String createdAt) {
        this.replyId = replyId;
        this.userSeq = userSeq;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getReplyId() {
        return replyId;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }



    public static ReplyResponse from(final Reply reply) {
        return new ReplyResponse(
                reply.getReplyId(),
                reply.getUserSeq(),
                reply.getContent(),
                reply.getCreatedAt()
        );
    }

}
