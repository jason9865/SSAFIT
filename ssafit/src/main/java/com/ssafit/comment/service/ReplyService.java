package com.ssafit.comment.service;

import com.ssafit.comment.model.dto.Reply;
import com.ssafit.comment.model.dto.ReplyModifyRequest;
import com.ssafit.comment.model.dto.ReplyRegisterRequest;

import java.util.List;

public interface ReplyService {

    List<Reply> getReplyList();

    List<Reply> getReplyByComment(int commentId);

    Reply findReply(int replyId);

    Reply findUserReply(int userSeq);

    boolean writeReply(final ReplyRegisterRequest replyRegisterRequest, final int commentId, final int userSeq);

    boolean modifyReply(final ReplyModifyRequest replyModifyRequest, final int replyId, final int userSeq);

    boolean removeReply(int replyId);

}
