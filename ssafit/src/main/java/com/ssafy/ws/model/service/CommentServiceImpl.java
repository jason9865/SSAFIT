package com.ssafy.ws.model.service;

import com.ssafy.ws.model.dao.CommentDao;
import com.ssafy.ws.model.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getList(int article_id) {
        return commentDao.selectAll()
                .stream()
                .filter(c -> c.getArticleId() == article_id)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public Comment getComment(int id) {
        return commentDao.selectOne(id);
    }

    @Override
    public void writeComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    @Override
    public void modifyComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void removeComment(int id) {
        commentDao.deleteComment(id);
    }
}
