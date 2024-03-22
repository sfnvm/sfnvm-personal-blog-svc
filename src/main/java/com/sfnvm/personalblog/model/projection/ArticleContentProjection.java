package com.sfnvm.personalblog.model.projection;

import java.time.Instant;

public record ArticleContentProjection(
    Long id,
    String title,
    Instant publishedDate,
    String content
) {

}
