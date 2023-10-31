package com.ssafit.video.service;

import com.ssafit.video.model.dto.Video;

import java.util.List;

public interface VideoService {
    List<Video> getList();

    Video searchVideoById(int id);


}
