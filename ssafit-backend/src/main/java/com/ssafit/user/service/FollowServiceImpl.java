package com.ssafit.user.service;

import com.ssafit.user.model.dao.FollowDao;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.dto.response.UserResponse;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowDao followDao;
    private final UserDao userDao;


    @Override
    public List<UserResponse> getFollowingUsers(int fromUserSeq) {
        return followDao.selectFollowingUsers(fromUserSeq).stream()
                .map((userSeq) -> UserResponse.from(userDao.selectByUserSeq(userSeq)))
                .collect(Collectors.toList())
                ;
    }

    @Override
    public int checkFollowing(int fromUserSeq, int toUserSeq) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("fromUserSeq",fromUserSeq);
        map.put("toUserSeq",toUserSeq);
        return followDao.isFollowing(map);
    }

    @Override
    @Transactional
    public boolean followUser(int fromUserSeq, int toUserSeq) {
        if(checkFollowing(fromUserSeq,toUserSeq) == 1){
            System.out.println("팔로우 안 돼. 안해줘. 돌아가.");
            return false;
        }
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("fromUserSeq",fromUserSeq);
        map.put("toUserSeq",toUserSeq);
        return followDao.followUser(map) > 0;
    }

    @Override
    @Transactional
    public boolean unfollowUser(int fromUserSeq, int toUserSeq) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("fromUserSeq",fromUserSeq);
        map.put("toUserSeq",toUserSeq);
        return followDao.unfollowUser(map) > 0;
    }
}
