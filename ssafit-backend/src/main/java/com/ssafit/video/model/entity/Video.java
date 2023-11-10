package com.ssafit.video.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Video {
    int videoId;
    String title;
    String part;
    String channelName;
    String url;
    int viewCnt;
    String createdAt;

    @Builder
    public Video(final int videoId, final String title, final String part, final String channelName,
                 final String url, final int viewCnt, final String createdAt) {
        this.videoId = videoId;
        this.title = title;
        this.part = part;
        this.channelName = channelName;
        this.url = url;
        this.viewCnt = viewCnt;
        this.createdAt = createdAt;
    }
}
