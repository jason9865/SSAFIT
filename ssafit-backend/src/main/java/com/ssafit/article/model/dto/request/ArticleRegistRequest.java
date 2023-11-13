package com.ssafit.article.model.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRegistRequest {
    private int boardId;
    private String title;
    private String content;
}
