package com.ssafit.user.model.dao;


import java.util.List;
import java.util.Map;

public interface FollowDao {
    List<Integer>selectFollowingUsers(int toUserSeq);

    int isFollowing(Map<String,Integer> map);

    int followUser(Map<String,Integer> map);

    int unfollowUser(Map<String,Integer> map);

}
