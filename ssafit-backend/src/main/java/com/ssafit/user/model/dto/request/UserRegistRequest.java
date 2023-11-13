package com.ssafit.user.model.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegistRequest {
    private String userId;
    private String userPwd;
    private String userConfirmedPwd;
    private String userName;
    private String nickName;
    private String email;
}
