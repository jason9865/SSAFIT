package com.ssafit.video.service;

import com.ssafit.video.model.dao.VideoDao;
import com.ssafit.video.model.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
    public List<Video> getListByPart(String part) {
    	return videoDao.selectByPart(part);
    }

    @Override
    public Video getVideoById(String videoId) {
        videoDao.updateViewCnt(videoId);
        return videoDao.selectById(videoId);
    }

    @Override
    public List<Video> getVideoLikeList(int userSeq){
        return videoDao.selectVideoLikeList(userSeq).stream()
                .map(videoDao::selectById)
                .collect(toList())
                ;
    }

    public int checkVideoLike(int userSeq, String videoId){
        return videoDao.findVideoLike(userSeq,videoId);
    }

    @Override
    public boolean doVideoLike(int userSeq, String videoId) {
        if (checkVideoLike(userSeq,videoId) == 1){
            System.out.println("이미 좋아요 했잖아. 안 돼. 안해줘. 돌아가.");
            return false;
        }
        return videoDao.doVideoLike(userSeq, videoId) > 0;
    }

    @Override
    public boolean undoVideoLike(int userSeq, String videoId) {
        return videoDao.undoVideoLike(userSeq,videoId) > 0;
    }


}
