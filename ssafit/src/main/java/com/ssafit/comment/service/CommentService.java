package com.ssafit.comment.service;

import com.ssafit.comment.model.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getList(int article_id);

    Comment getComment(int id);

    boolean writeComment(Comment comment);

    boolean modifyComment(Comment comment);

    boolean removeComment(int id);

}
