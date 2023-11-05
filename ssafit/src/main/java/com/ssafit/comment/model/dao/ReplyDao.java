package com.ssafit.comment.model.dao;

import com.ssafit.comment.model.entity.Reply;

import java.util.List;

public interface ReplyDao {

    List<Reply> selectAll();

    Reply selectReply(int replyId);

    Reply selectReplyByUser(int userSeq);

    int insertReply(Reply reply);

    int updateReply(Reply reply);

    int deleteReply(int replyId);
}
