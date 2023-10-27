package com.ssafy.ws.controller;

import com.ssafy.ws.model.dto.Video;
import com.ssafy.ws.model.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<?> getVideoList(){
        System.out.println("getVideoList 도착");
        List<Video> videoList = videoService.getList();
        return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);

    }

    @GetMapping("/{videoId}")
    public ResponseEntity<?> searchVideoById(@PathVariable int videoId) {

        Video video = videoService.searchVideoById(videoId);
        return new ResponseEntity<Video>(video, HttpStatus.OK);
    }

}
