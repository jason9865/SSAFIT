package com.ssafit.comment.model.dto;


public class ReplyRegisterRequest {
    private String content;

    public ReplyRegisterRequest() {
    }

    public ReplyRegisterRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
