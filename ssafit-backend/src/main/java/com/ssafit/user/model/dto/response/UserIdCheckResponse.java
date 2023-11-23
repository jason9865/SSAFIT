package com.ssafit.user.model.dto.response;

import com.ssafit.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserIdCheckResponse {
    private String message;
    private Boolean isValid;


}
