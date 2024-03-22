package com.sfnvm.personalblog.model.io.response;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleWithContentResponse {

  private Long id;

  private String title;

  private Instant publishedDate;

  private String content;
}
