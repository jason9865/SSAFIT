package com.ssafit.article.model.dto.response;

import com.ssafit.article.model.entity.Article;
import com.ssafit.board.model.entity.Board;
import com.ssafit.user.model.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class ArticleResponse {
    private int articleId;
    private String title;
    private String content;
    private int boardId;
    private String boardName;
    private int viewCnt;
    private String createdAt;
    private String modifiedAt;
    private int userSeq;
    private String userId;
    private String nickName;

    public static ArticleResponse from(
            final Article article,
            final User user,
            final Board board) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getContent(),
                article.getBoardId(),
                board.getName(),
                article.getViewCnt(),
                article.getCreatedAt(),
                article.getModifiedAt(),
                article.getUserSeq(),
                user.getUserId(),
                user.getNickName()
        );
    }

}
