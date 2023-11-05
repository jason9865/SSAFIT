package com.ssafit.comment.model.mapper;

import com.ssafit.comment.model.dto.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    List<Reply> selectAll();

    Reply selectReply(int replyId);

    Reply selectReplyByUser(int userSeq);

    int insertReply(Reply reply);

    int updateReply(Reply reply);

    int deleteReply(int replyId);

}
