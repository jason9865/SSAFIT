package com.ssafit.article.model.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRegistDto {
    private int boardId;
    private String title;
    private String content;
}
