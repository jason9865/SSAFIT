package com.ssafit.comment.service;

import com.ssafit.comment.model.dao.CommentDao;
import com.ssafit.comment.model.dao.ReplyDao;
import com.ssafit.comment.model.dto.response.ReplyResponse;
import com.ssafit.comment.model.entity.Comment;
import com.ssafit.comment.model.entity.Reply;
import com.ssafit.comment.model.dto.resquest.ReplyModifyRequest;
import com.ssafit.comment.model.dto.resquest.ReplyRegisterRequest;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements  ReplyService{

    private final ReplyDao replyDao;
    private final CommentDao commentDao;
    private final UserDao userDao;
    
    @Override
    public List<Reply> getReplyList() {
        return replyDao.selectAll();
    }

    @Override
    public List<ReplyResponse> getReplyByComment(int commentId) {
        Comment comment = commentDao.selectOne(commentId);
        return replyDao.selectAll().stream()
                .filter(r -> r.getCommentId() == commentId)
                .map(ReplyResponse::from)
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
        final Reply reply =
                Reply.builder()
                        .content(request.getContent())
                        .commentId(commentId)
                        .userSeq(userSeq)
                        .build();
        return replyDao.insertReply(reply) > 0;
    }

    @Override
    public boolean modifyReply(final ReplyModifyRequest request, final int replyId, final int userSeq) {
        final User user = userDao.selectByUserSeq(userSeq);
        final Reply reply = findReply(replyId);
        final Comment comment = commentDao.selectOne(reply.getCommentId());

//        Reply newReply = new Reply();
//        newReply.setUserSeq(reply.getUserSeq());
//        newReply.setContent(request.getContent());
//        newReply.setUserSeq(user.getUserSeq());
//        newReply.setCommentId(comment.getCommentId());

        final Reply newReply =
                Reply.builder()
                        .replyId(reply.getReplyId())
                        .userSeq(reply.getUserSeq())
                        .content(request.getContent())
                        .userSeq(user.getUserSeq())
                        .commentId(comment.getCommentId())
                        .build();
        return replyDao.updateReply(newReply) > 0;
    }

    @Override
    public boolean removeReply(final int replyId, final int userSeq) {
        final User user = userDao.selectByUserSeq(userSeq);
        final Reply reply = findReply(replyId);


        return replyDao.deleteReply(replyId) > 0;
    }


}
