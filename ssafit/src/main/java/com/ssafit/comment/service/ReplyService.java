package com.ssafit.comment.service;

import com.ssafit.comment.model.dto.response.ReplyResponse;
import com.ssafit.comment.model.entity.Reply;
import com.ssafit.comment.model.dto.resquest.ReplyModifyRequest;
import com.ssafit.comment.model.dto.resquest.ReplyRegisterRequest;

import java.util.List;

public interface ReplyService {

    List<Reply> getReplyList();

    List<ReplyResponse> getReplyByComment(int commentId);

    Reply findReply(int replyId);

    Reply findUserReply(int userSeq);

    boolean writeReply(final ReplyRegisterRequest replyRegisterRequest, final int commentId, final int userSeq);

    boolean modifyReply(final ReplyModifyRequest replyModifyRequest, final int replyId, final int userSeq);

    boolean removeReply(final int replyId, final int userSeq);

}
