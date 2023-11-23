package com.ssafit.video.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoLike {
    private int videoLikeId;
    private String videoId;
    private int userSeq;

    @Builder
    public VideoLike(final int videoLikeId, final String videoId, final int userSeq){
        this.videoLikeId=videoLikeId;
        this.videoId=videoId;
        this.userSeq=userSeq;
    }

}
