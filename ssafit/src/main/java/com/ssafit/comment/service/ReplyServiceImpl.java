package com.ssafit.comment.service;

import com.ssafit.board.model.dao.BoardDao;
import com.ssafit.comment.model.dao.CommentDao;
import com.ssafit.comment.model.dao.ReplyDao;
import com.ssafit.comment.model.dto.Comment;
import com.ssafit.comment.model.dto.Reply;
import com.ssafit.comment.model.dto.ReplyModifyRequest;
import com.ssafit.comment.model.dto.ReplyRegisterRequest;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class ReplyServiceImpl implements  ReplyService{

    private final ReplyDao replyDao;
    private final CommentDao commentDao;
    private final UserDao userDao;


    @Autowired
    public ReplyServiceImpl(ReplyDao replyDao, CommentDao commentDao, UserDao userDao) {
        this.replyDao = replyDao;
        this.commentDao = commentDao;
        this.userDao = userDao;
    }



    @Override
    public List<Reply> getReplyList() {
        return replyDao.selectAll();
    }

    @Override
    public List<Reply> getReplyByComment(int commentId) {
        System.out.println(replyDao.selectAll());
        return replyDao.selectAll().stream()
                .filter(r -> r.getCommentId() == commentId)
                .collect(toList())
                ;
    }

    @Override
    public Reply findReply(int replyId) {
        return replyDao.selectReply(replyId);
    }

    public Reply findUserReply(int userSeq) {
        return replyDao.selectReplyByUser(userSeq);
    }


    @Override
    public boolean writeReply(final ReplyRegisterRequest request, final int commentId, final int userSeq) {

        final Reply reply = new Reply();
        reply.setContent(request.getContent());
        reply.setCommentId(commentId);
        reply.setUserSeq(userSeq);

        return replyDao.insertReply(reply) > 0;
    }

    @Override
    public boolean modifyReply(final ReplyModifyRequest request, final int replyId, final int userSeq) {
        final Comment comment = commentDao.selectOne(request.getCommentId());
        final User user = userDao.selectByUserSeq(userSeq);
        final Reply reply = findReply(replyId);

        Reply newReply = new Reply();
        newReply.setUserSeq(reply.getUserSeq());
        newReply.setContent(request.getContent());
        newReply.setUserSeq(user.getUserSeq());
        newReply.setCommentId(comment.getCommentId());

        return replyDao.updateReply(newReply) > 0;
    }

    @Override
    public boolean removeReply(int replyId) {
        return replyDao.deleteReply(replyId) > 0;
    }

}
