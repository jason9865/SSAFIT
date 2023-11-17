package com.ssafit.user.controller;

import com.ssafit.article.service.ArticleService;
import com.ssafit.comment.service.CommentService;
import com.ssafit.user.model.dto.response.UserResponse;
import com.ssafit.user.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags="팔로우 컨트롤러")
@CrossOrigin("http://localhost:5173/")
public class FollowController {

    private final FollowService followService;

    @GetMapping("/getFollowList")
    @ApiOperation(value="로그인 유저가 팔로우하고 있는 유저리스트를 불러옵니다.")
    public ResponseEntity<List<UserResponse>> getFollowList(HttpServletRequest request) {
        int fromUserSeq = Integer.parseInt(request.getHeader("userSeq")); // 로그인 유저 seq
        return new ResponseEntity<List<UserResponse>>(followService.getFollowingUsers(fromUserSeq),HttpStatus.OK);
    }

    @GetMapping("/checkFollowing/{toUserSeq}")
    @ApiOperation(value = "로그인 유저가 해당 유저를 팔로우하고 있는지 확인")
    public ResponseEntity<Integer> checkFollowing(@PathVariable int toUserSeq, HttpServletRequest request){
        int fromUserSeq = Integer.parseInt(request.getHeader("userSeq")); // 로그인 유저 seq
        return new ResponseEntity<Integer>(followService.checkFollowing(fromUserSeq,toUserSeq), HttpStatus.OK);
    }

    @GetMapping("/follow/{toUserSeq}")
    @ApiOperation(value = "로그인 유저가 해당 유저를 팔로우합니다.")
    public ResponseEntity<Boolean> follow(@PathVariable int toUserSeq, HttpServletRequest request){
        int fromUserSeq = Integer.parseInt(request.getHeader("userSeq")); // 로그인 유저 seq
        boolean isFollowed = followService.followUser(fromUserSeq,toUserSeq);
        if(!isFollowed)
            return new ResponseEntity<Boolean>(isFollowed,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isFollowed,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/unfollow/{toUserSeq}")
    @ApiOperation(value = "로그인 유저가 해당 유저를 언팔로우합니다.")
    public ResponseEntity<Boolean> unfollow(@PathVariable int toUserSeq, HttpServletRequest request){
        int fromUserSeq = Integer.parseInt(request.getHeader("userSeq")); // 로그인 유저 seq
        boolean isUnfollowed = followService.unfollowUser(fromUserSeq,toUserSeq);
        if(!isUnfollowed)
            return new ResponseEntity<Boolean>(isUnfollowed,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isUnfollowed,HttpStatus.ACCEPTED);
    }




}
