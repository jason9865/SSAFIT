package com.ssafit.comment.service;

import com.ssafit.comment.model.dao.CommentDao;
import com.ssafit.comment.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

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
    public boolean writeComment(Comment comment) {
        return commentDao.insertComment(comment) > 0;
    }

    @Override
    public boolean modifyComment(Comment comment) {
        return commentDao.updateComment(comment) > 0;
    }

    @Override
    public boolean removeComment(int id) {
        return commentDao.deleteComment(id) > 0;
    }
}
