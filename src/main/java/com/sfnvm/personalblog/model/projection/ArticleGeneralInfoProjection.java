package com.sfnvm.personalblog.model.projection;

import java.time.Instant;

public record ArticleGeneralInfoProjection(
    Long id,
    String title,
    Instant publishedDate
) {

}
