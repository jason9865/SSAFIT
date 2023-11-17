package com.ssafit.comment.service;

import com.ssafit.comment.model.dto.response.CommentResponse;
import com.ssafit.comment.model.dto.resquest.CommentModifyRequest;
import com.ssafit.comment.model.dto.resquest.CommentRegistRequest;
import com.ssafit.comment.model.entity.Comment;
import com.ssafit.comment.model.entity.CommentDislike;
import com.ssafit.comment.model.entity.CommentLike;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getList(int article_id);

    CommentResponse getComment(int commentId);

    boolean writeComment(CommentRegistRequest request, int articleId, int userSeq);

    boolean modifyComment(CommentModifyRequest request, int commentId, int userSeq);

    boolean removeComment(int id);

    int getLikeCount(int commentId);

    boolean addCommentLike(int commentId, int userSeq);

    boolean deleteCommentLike(int commentLikeId);

    int getDislikeCount(int commentId);

    boolean addCommentDislike(int commentId, int userSeq);

    boolean deleteCommentDislike(int commentDislikeId);

    CommentLike findCommentLike(int commentId, int userSeq);

    CommentDislike findCommentDislike(int commentId, int userSeq);

    boolean isAvailable(int commentId, int userSeq);
}
