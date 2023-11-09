package com.ssafit.video.service;

import com.ssafit.video.model.dao.VideoDao;
import com.ssafit.video.model.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoDao videoDao;

    @Autowired
    public VideoServiceImpl(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @Override
    public List<Video> getList() {
        return videoDao.selectAll();
    }

    @Override
    public Video searchVideoById(int videoId) {
        videoDao.updateViewCnt(videoId);
        return videoDao.selectById(videoId);
    }


}
