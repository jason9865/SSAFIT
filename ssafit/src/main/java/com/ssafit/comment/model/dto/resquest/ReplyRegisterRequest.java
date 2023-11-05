package com.ssafit.comment.model.dto.resquest;


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

}
