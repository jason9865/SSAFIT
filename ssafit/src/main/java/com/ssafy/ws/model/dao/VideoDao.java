package com.ssafy.ws.model.dao;

import com.ssafy.ws.model.dto.Video;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll();

    Video selectById(int id);

    int updateViewCnt(int id);

}
