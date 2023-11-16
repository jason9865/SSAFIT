package com.ssafit.video.model.dao;

import com.ssafit.video.model.entity.Video;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll();
    
    List<Video> selectByPart(String part);

    Video selectById(String videoId);

    int updateViewCnt(String videoId);

}
