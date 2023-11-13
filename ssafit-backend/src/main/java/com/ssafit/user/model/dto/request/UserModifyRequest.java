package com.ssafit.user.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserModifyRequest {
    private String userPwd;
    private String userConfirmedPwd;
    private String userName;
    private String nickName;
    private String email;
}
