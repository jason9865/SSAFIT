package com.ssafit.video.model.entity;

import com.ssafit.video.controller.VideoController;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoComment {
    private int videoCommentId;
    private String content;
    private int userSeq; // header에 담아서
    private String videoId; // data에 넣어서
    private String createdAt;

    @Builder
    public VideoComment(final int videoCommentId, final String content, final int userSeq,
                        final String videoId, final String createdAt){
        this.videoCommentId = videoCommentId;
        this.content = content;
        this.userSeq = userSeq;
        this.videoId = videoId;
        this.createdAt = createdAt;
    }

}
