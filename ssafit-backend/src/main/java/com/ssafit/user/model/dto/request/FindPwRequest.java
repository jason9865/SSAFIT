package com.ssafit.user.model.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindPwRequest {
	@NotBlank(message = "아이디를 반드시 입력해주세요.")
    private String userId;
    @NotBlank(message = "이메일을 반드시 입력해주세요.")
    private String email;

}
