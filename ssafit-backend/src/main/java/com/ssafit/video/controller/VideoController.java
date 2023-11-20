package com.ssafit.video.controller;

import com.ssafit.video.model.entity.Video;
import com.ssafit.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/video")
@Api(tags="비디오 컨트롤러")
@CrossOrigin("http://localhost:5173/")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    @ApiOperation(value="전체 비디오 리스트 조회", notes="검색조건 추후 추가 예정")
    public ResponseEntity<?> getVideoList(){
        List<Video> videoList = videoService.getList();
        return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
    }
    
    @GetMapping("/{part}")
    @ApiOperation(value="부위별 비디오 조회", notes="검색조건 추후 추가 예정")
    public ResponseEntity<?> getVideoListByPart(@PathVariable String part){
        List<Video> videoList = videoService.getListByPart(part);
        return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
    }

    @GetMapping("/{videoId}/detail")
    @ApiOperation(value="비디오 개별 조회", notes="검색조건 추후 추가 예정")
    public ResponseEntity<?> getVideoById(@PathVariable String videoId) {

        Video video = videoService.getVideoById(videoId);
        return new ResponseEntity<Video>(video, HttpStatus.OK);
    }

    @GetMapping("/videoLikeList")
    @ApiOperation(value="좋아요한 비디오 조회")
    public ResponseEntity<List<Video>> getVideoLikeList(HttpServletRequest request){
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));
        List<Video> videoLikeList = videoService.getVideoLikeList(userSeq);
        return new ResponseEntity<List<Video>>(videoLikeList,HttpStatus.OK);
    }

    @GetMapping("/{videoId}/isLikeVideo")
    @ApiOperation(value="해당 비디오를 좋아요했는지 확인")
    public ResponseEntity<Integer> checkVideoLike(@PathVariable String videoId, HttpServletRequest request){
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));
        return new ResponseEntity<Integer>(videoService.checkVideoLike(userSeq,videoId),HttpStatus.OK);
    }

    @GetMapping("/{videoId}/doVideoLike")
    @ApiOperation(value="비디오 좋아요")
    public ResponseEntity<Boolean> doVideoLike(@PathVariable String videoId, HttpServletRequest request){
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));
        boolean isLikeDone = videoService.doVideoLike(userSeq,videoId);
        if (!isLikeDone)
            return new ResponseEntity<Boolean>(isLikeDone,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isLikeDone,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{videoId}/undoVideoLike")
    @ApiOperation(value="비디오 좋아요")
    public ResponseEntity<Boolean> undoVideoLike(@PathVariable String videoId, HttpServletRequest request){
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));
        boolean isLikeUndone = videoService.undoVideoLike(userSeq,videoId);
        if (!isLikeUndone)
            return new ResponseEntity<Boolean>(isLikeUndone,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isLikeUndone,HttpStatus.ACCEPTED);
    }

}
