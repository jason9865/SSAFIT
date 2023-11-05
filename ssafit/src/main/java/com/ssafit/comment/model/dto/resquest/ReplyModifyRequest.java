package com.ssafit.comment.model.dto.resquest;


public class ReplyModifyRequest {
    private String content;
    private int commentId;

    public ReplyModifyRequest() {
    }

    public ReplyModifyRequest(String content, int commentId) {
        this.content = content;
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
