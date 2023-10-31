package com.ssafit.video.model.dao;

import com.ssafit.video.model.dto.Video;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll();

    Video selectById(int id);

    int updateViewCnt(int id);

}
