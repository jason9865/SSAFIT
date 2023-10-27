package com.ssafy.ws.model.service;

import com.ssafy.ws.model.dto.Video;

import java.util.List;

public interface VideoService {
    List<Video> getList();

    Video searchVideoById(int id);


}
