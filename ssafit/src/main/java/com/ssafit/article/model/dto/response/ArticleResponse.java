package com.ssafit.article.model.dto.response;

import com.ssafit.article.model.entity.Article;
import com.ssafit.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private int articleId;
    private String title;
    private String content;
    private int boardId;
    private int userSeq;
    private String userId;
    private int viewCnt;
    private String createdAt;
    private String modifiedAt;

    public static ArticleResponse of(
            final Article article,
            final User user) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getContent(),
                article.getBoardId(),
                article.getUserSeq(),
                user.getUserId(),
                article.getViewCnt(),
                article.getCreatedAt(),
                article.getModifiedAt()

        );
    }

}
