package com.ssafit.comment.model.dao;

import com.ssafit.comment.model.entity.Comment;
import com.ssafit.comment.model.entity.CommentDislike;
import com.ssafit.comment.model.entity.CommentLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
	
	List<Comment> selectAll();
	
	Comment selectOne(int commentId);
	
	int insertComment(Comment comment);
	
	int updateComment(Comment comment);
	
	int deleteComment(int commentId);
	
	// 댓글 좋아요

	int selectLikeCount (int commentId);

	CommentLike selectCommentLike(@Param("commentId") int commentId, @Param("userSeq") int userSeq);
	
	int insertLike(CommentLike commentLike);

	int deleteLike(int commentLikeId);
	
	// 댓글 싫어요

	int selectDislikeCount(int commentId);

	CommentDislike selectCommentDislike(@Param("commentId") int commentId, @Param("userSeq") int userSeq);
	
	int insertDislike(CommentDislike commentDislike);

	int deleteDislike(int commentDislikeId);

}
