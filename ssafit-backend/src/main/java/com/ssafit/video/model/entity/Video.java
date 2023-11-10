package com.ssafit.video.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    int videoId;
    String title;
    String part;
    String channelName;
    String url;
    int viewCnt;
    String createdAt;

}