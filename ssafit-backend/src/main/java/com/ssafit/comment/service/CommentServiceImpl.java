package com.ssafit.comment.service;

import com.ssafit.article.model.dao.ArticleDao;
import com.ssafit.article.model.entity.Article;
import com.ssafit.board.model.dao.BoardDao;
import com.ssafit.board.model.entity.Board;
import com.ssafit.comment.model.dao.CommentDao;
import com.ssafit.comment.model.dto.response.CommentResponse;
import com.ssafit.comment.model.dto.resquest.CommentModifyRequest;
import com.ssafit.comment.model.dto.resquest.CommentRegistRequest;
import com.ssafit.comment.model.entity.Comment;
import com.ssafit.comment.model.entity.CommentDislike;
import com.ssafit.comment.model.entity.CommentLike;
import com.ssafit.user.model.dao.UserDao;
import com.ssafit.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final UserDao userDao;
    private final ArticleDao articleDao;

    @Override
    public List<CommentResponse> getList(int article_id) {

        return commentDao.selectAll()
                .stream()
                .filter(c -> c.getArticleId() == article_id)
                .map(c -> CommentResponse.of(c,userDao.selectByUserSeq(c.getUserSeq()) ))
                .collect(Collectors.toList())
                ;
    }

    @Override
    public CommentResponse getComment(int commentId) {
        Comment comment = commentDao.selectOne(commentId);
        User user = userDao.selectByUserSeq(comment.getUserSeq());
        return CommentResponse.of(commentDao.selectOne(commentId),user);
    }

    @Override
    public boolean writeComment(CommentRegistRequest request, int articleId, int userSeq) {
        Article article = articleDao.selectById(articleId);
        User user = userDao.selectByUserSeq(userSeq);

        Comment comment =
                Comment.builder()
                        .content(request.getContent())
                        .userSeq(user.getUserSeq())
                        .articleId(article.getArticleId())
                        .boardId(article.getBoardId())
                        .build();
        return commentDao.insertComment(comment) > 0;
    }

    @Override
    public boolean modifyComment(CommentModifyRequest request,int commentId, int userSeq) {
        Comment comment = commentDao.selectOne(commentId);
        User user = userDao.selectByUserSeq(userSeq);
        Comment newComment =
                Comment.builder()
                        .commentId(comment.getCommentId())
                        .content(request.getContent())
                        .userSeq(user.getUserSeq())
                        .articleId(comment.getArticleId())
                        .boardId(comment.getBoardId())
                        .build();

        return commentDao.updateComment(newComment) > 0;
    }

    @Override
    public boolean removeComment(int id) {
        return commentDao.deleteComment(id) > 0;
    }

    @Override
    public int getLikeCount(int commentId) {
        return commentDao.selectLikeCount(commentId);
    }

    @Override
    public boolean addCommentLike(int commentId, int userSeq) {
        if(!isAvailable(commentId,userSeq)) {
            System.out.println("댓글 좋아요 안 돼. 안 해줘. 돌아가");
            return false;
        }

        CommentLike newCommentLike =
                CommentLike.builder()
                        .userSeq(userSeq)
                        .commentId(commentId)
                        .build();

        return commentDao.insertLike(newCommentLike) > 0;
    }

    @Override
    public boolean deleteCommentLike(int commentLikeId) {
        return commentDao.deleteLike(commentLikeId) > 0;
    }

    @Override
    public int getDislikeCount(int commentId) {
        return commentDao.selectDislikeCount(commentId);
    }

    @Override
    public boolean addCommentDislike(int commentId, int userSeq) {
        if(!isAvailable(commentId,userSeq)) {
            System.out.println("댓글 싫어요 안 돼. 안 해줘. 돌아가");
            return false;
        }

        CommentDislike newCommentDislike =
                CommentDislike.builder()
                        .commentId(commentId)
                        .userSeq(userSeq)
                        .build();

        return commentDao.insertDislike(newCommentDislike) > 0;
    }

    @Override
    public boolean deleteCommentDislike(int commentDislikeId) {
        return commentDao.deleteDislike(commentDislikeId) > 0;
    }

    @Override
    public CommentLike findCommentLike(int commentId, int userSeq) {
        return commentDao.selectCommentLike(commentId,userSeq);
    }

    @Override
    public CommentDislike findCommentDislike(int commentId, int userSeq) {
        return commentDao.selectCommentDislike(commentId,userSeq);
    }

    // 좋아요와 싫어요를 둘 다 누른 적이 없어야 한다.
    @Override
    public boolean isAvailable(int commentId, int userSeq){
        return findCommentLike(commentId, userSeq) == null && findCommentDislike(commentId, userSeq) == null;
    }
}
