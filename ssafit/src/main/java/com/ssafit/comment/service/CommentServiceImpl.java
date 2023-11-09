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
                .map(c -> new CommentResponse(c,
                        userDao.selectByUserSeq(c.getUserSeq())))
                .collect(Collectors.toList())
                ;
    }

    @Override
    public CommentResponse getComment(int commentId) {
        Comment comment = commentDao.selectOne(commentId);
        User user = userDao.selectByUserSeq(comment.getUserSeq());
        return new CommentResponse(commentDao.selectOne(commentId),user);
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
}
