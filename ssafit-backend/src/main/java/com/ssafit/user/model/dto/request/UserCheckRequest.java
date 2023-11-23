package com.ssafit.user.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCheckRequest {
    private String userId;
    private String nickName;
    private String email;
}
