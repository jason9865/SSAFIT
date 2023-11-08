package com.ssafit.user.model.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @NotBlank(message = "아이디를 반드시 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 반드시 입력해주세요.")
    private String userPwd;


}
