package com.ssafit.video.model.dao;

import com.ssafit.video.model.entity.Video;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll();

    Video selectById(int videoId);

    int updateViewCnt(int videoId);

    int updateVideo(Video video);

    int deleteVideo(int videoId);

}
