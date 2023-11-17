package com.ssafit.video.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    String videoId;
    String title;
    String channelTitle;
    String description;
    String part;
    int viewCnt;

}