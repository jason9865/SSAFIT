package com.ssafit.video.model.dao;

import com.ssafit.video.model.entity.Video;
import com.ssafit.video.model.entity.VideoLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll();
    
    List<Video> selectByPart(String part);

    Video selectById(String videoId);

    int updateViewCnt(String videoId);

    List<String> selectVideoLikeList (int userSeq);

    int findVideoLike(@Param("userSeq") int userSeq, @Param("videoId") String videoId);

    int doVideoLike(@Param("userSeq") int userSeq, @Param("videoId") String videoId);

    int undoVideoLike(@Param("userSeq") int userSeq, @Param("videoId") String videoId);

}
