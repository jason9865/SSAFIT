package com.ssafit.comment.model.mapper;

import com.ssafit.comment.model.entity.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 아직 안 씀 -> 쓸까말까
@Mapper
public interface ReplyMapper {
    List<Reply> selectAll();

    Reply selectReply(int replyId);

    Reply selectReplyByUser(int userSeq);

    int insertReply(Reply reply);

    int updateReply(Reply reply);

    int deleteReply(int replyId);

}
