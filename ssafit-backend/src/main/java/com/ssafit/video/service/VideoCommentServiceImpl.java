package com.ssafit.video.service;

import com.ssafit.user.model.dao.UserDao;
import com.ssafit.video.model.dao.VideoCommentDao;
import com.ssafit.video.model.dto.request.VideoCommentCreateRequest;
import com.ssafit.video.model.dto.request.VideoCommentModifyRequest;
import com.ssafit.video.model.dto.response.VideoCommentResponse;
import com.ssafit.video.model.entity.VideoComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoCommentServiceImpl implements VideoCommentService{

    private final VideoCommentDao videoCommentDao;
    private final UserDao userDao;
    @Override
    public List<VideoCommentResponse> getVideoCommentList(String videoId) {
        return videoCommentDao.selectAll(videoId).stream()
                .map(videoComment -> VideoCommentResponse.of(
                        videoComment,
                        userDao.selectByUserSeq(videoComment.getUserSeq())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoCommentResponse> getVideoCommentList(int userSeq) {
        return videoCommentDao.selectByUserSeq(userSeq).stream()
                .map(videoComment -> VideoCommentResponse.of(
                        videoComment,
                        userDao.selectByUserSeq(userSeq)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public VideoCommentResponse getVideoComment(int videoCommentId) {
        VideoComment videoComment = videoCommentDao.selectById(videoCommentId);
        int userSeq = videoComment.getUserSeq();
        return VideoCommentResponse.of(videoComment, userDao.selectByUserSeq(userSeq));
    }

    @Override
    public boolean writeVideoComment(VideoCommentCreateRequest request, int userSeq, String videoId) {
        VideoComment videoComment = VideoComment.builder()
                .content(request.getContent())
                .userSeq(userSeq)
                .videoId(videoId)
                .build();
        return videoCommentDao.insertVideoComment(videoComment) > 0;
    }

    @Override
    public boolean modifyVideoComment(VideoCommentModifyRequest request, int userSeq, int videoCommentId) {
        VideoComment videoComment = videoCommentDao.selectById(videoCommentId);
        VideoComment newComment = VideoComment.builder()
                .videoCommentId(videoCommentId)
                .content(request.getContent())
                .userSeq(userSeq)
                .videoId(videoComment.getVideoId())
                .build();
        return videoCommentDao.updateVideoComment(newComment) > 0;
    }

    @Override
    public boolean removeVideoComment(int videoCommentId) {
        return videoCommentDao.deleteVideoComment(videoCommentId) > 0;
    }
}
