package com.ssafit.comment.model.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {
    private int replyId;
    private String content;
    private int userSeq;
    private int commentId;
    private String createdAt;

    @Builder
    public Reply(
            final int replyId, final String content, final int userSeq, final int commentId, final String createdAt) {
        this.replyId = replyId;
        this.content = content;
        this.userSeq = userSeq;
        this.commentId = commentId;
        this.createdAt = createdAt;
    }
}
