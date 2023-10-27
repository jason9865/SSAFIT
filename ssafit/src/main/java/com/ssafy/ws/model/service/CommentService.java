package com.ssafy.ws.model.service;

import com.ssafy.ws.model.dto.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getList(int article_id);

    Comment getComment(int id);

    void writeComment(Comment comment);

    void modifyComment(Comment comment);

    void removeComment(int id);

}
