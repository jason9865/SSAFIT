package com.ssafit.video.model.dto;

public class Video {
    int videoId;
    String title;
    String part;
    String channelName;
    String url;
    int viewCnt;
    String createdAt;

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int id) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", title='" + title + '\'' +
                ", part='" + part + '\'' +
                ", channelName='" + channelName + '\'' +
                ", url='" + url + '\'' +
                ", viewCnt=" + viewCnt +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
