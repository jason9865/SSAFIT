package com.ssafit.comment.model.dto.response;

import com.ssafit.comment.model.entity.Comment;
import com.ssafit.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private int commentId;
    private String content;
    private int userSeq;
    private String userId;
    private String nickName;
    private int articleId;
    private int boardId;
    private String createdAt;

//    public static CommentResponse from(final Comment comment, User user){
//        return new CommentResponse(
//                comment.getCommentId(),
//                comment.getContent(),
//                comment.getUserSeq(),
//                user.getUserId(),
//                user.getNickName(),
//                comment.getArticleId(),
//                comment.getBoardId(),
//                comment.getCreatedAt());
//    }

    @Builder
    public CommentResponse(Comment comment, User user) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userSeq = comment.getUserSeq();
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
        this.articleId = comment.getArticleId();
        this.boardId = comment.getBoardId();
        this.createdAt = comment.getCreatedAt();
    }
}
