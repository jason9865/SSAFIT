package com.ssafit.user.model.dto.response;

import com.ssafit.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String nickName;
    private String email;
    private int userRank; // 관리자 or 유저
    private String joinedAt;

    public static UserResponse from(final User user) {
        return new UserResponse(
                user.getUserSeq(),
                user.getUserId(),
                user.getUserPwd(),
                user.getUserName(),
                user.getNickName(),
                user.getEmail(),
                user.getUserRank(),
                user.getJoinedAt()
        );
    }


}
