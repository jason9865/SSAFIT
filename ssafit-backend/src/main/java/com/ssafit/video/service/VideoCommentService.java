package com.ssafit.video.service;

import com.ssafit.video.model.dto.request.VideoCommentCreateRequest;
import com.ssafit.video.model.dto.request.VideoCommentModifyRequest;
import com.ssafit.video.model.dto.response.VideoCommentResponse;

import java.util.List;

public interface VideoCommentService {
    List<VideoCommentResponse> getVideoCommentList(String videoId);

    List<VideoCommentResponse> getVideoCommentList(int userSeq);

    VideoCommentResponse getVideoComment(int videoCommentId);

    boolean writeVideoComment(VideoCommentCreateRequest request, int userSeq, String videoId);

    boolean modifyVideoComment(VideoCommentModifyRequest request, int userSeq, int videoCommentId);

    boolean removeVideoComment(int videoCommentId);


}
