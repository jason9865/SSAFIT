package com.ssafit.video.model.dto.response;

import com.ssafit.user.model.entity.User;
import com.ssafit.video.model.entity.Video;
import com.ssafit.video.model.entity.VideoComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoCommentResponse {
    private int videoCommentId;
    private String content;
    private int userSeq; // header에 담아서
    private String videoId; // data에 넣어서
    private String createdAt;
    private String userId;
    private String nickName;
    private String userName;
    private String email;
    private String joinedAt;

    public static VideoCommentResponse of (VideoComment videoComment, User user) {
        return new VideoCommentResponse(
                videoComment.getVideoCommentId(),
                videoComment.getContent(),
                videoComment.getUserSeq(),
                videoComment.getVideoId(),
                videoComment.getCreatedAt(),
                user.getUserId(),
                user.getNickName(),
                user.getUserName(),
                user.getEmail(),
                user.getJoinedAt()
        );
    }
}
