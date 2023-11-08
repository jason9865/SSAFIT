package com.ssafit.article.model.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRegistDto {
    private String title;
    private String content;
}
