package com.ssafit.article.model.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleModifyDto {
    private String title;
    private String content;
}
