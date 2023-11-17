package com.ssafit.user.service;

import com.ssafit.user.model.dto.response.UserResponse;

import java.util.List;

public interface FollowService {
    List<UserResponse> getFollowingUsers(int fromUserSeq);

    int checkFollowing(int fromUserSeq, int toUserSeq);

    boolean followUser(int fromUserSeq, int toUserSeq);

    boolean unfollowUser(int fromUserSeq, int toUserSeq);
}
