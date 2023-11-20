package com.ssafit.video.model.dao;

import com.ssafit.video.model.dto.request.VideoCommentCreateRequest;
import com.ssafit.video.model.entity.VideoComment;

import java.util.List;

public interface VideoCommentDao {
    List<VideoComment> selectAll(String videoId);

    VideoComment selectById (int videoCommentId);

    List<VideoComment> selectByUserSeq(int userSeq);

    int insertVideoComment(VideoComment videoComment);

    int updateVideoComment(VideoComment videoComment);

    int deleteVideoComment(int videoCommentId);
}
