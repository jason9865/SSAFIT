package com.ssafit.user.model.dto.request;


import javax.validation.constraints.NotBlank;

public class UserLoginRequest {
    @NotBlank(message = "아이디를 반드시 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 반드시 입력해주세요.")
    private String userPwd;

    public UserLoginRequest(){
    }

    public UserLoginRequest(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPwd() {
        return userPwd;
    }


}
