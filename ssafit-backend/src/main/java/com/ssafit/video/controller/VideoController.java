package com.ssafit.video.controller;

import com.ssafit.video.model.entity.Video;
import com.ssafit.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
@Api(tags="비디오 컨트롤러")
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

    @GetMapping("/{videoId}")
    @ApiOperation(value="비디오 개별 조회", notes="검색조건 추후 추가 예정")
    public ResponseEntity<?> getVideoById(@PathVariable String videoId) {

        Video video = videoService.getVideoById(videoId);
        return new ResponseEntity<Video>(video, HttpStatus.OK);
    }



}
