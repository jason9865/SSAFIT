package com.ssafit.comment.service;

import com.ssafit.comment.model.dto.response.CommentResponse;
import com.ssafit.comment.model.dto.resquest.CommentModifyRequest;
import com.ssafit.comment.model.dto.resquest.CommentRegistRequest;
import com.ssafit.comment.model.entity.Comment;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getList(int article_id);

    CommentResponse getComment(int commentId);

    boolean writeComment(CommentRegistRequest request, int articleId, int userSeq);

    boolean modifyComment(CommentModifyRequest request, int commentId, int userSeq);

    boolean removeComment(int id);

}
