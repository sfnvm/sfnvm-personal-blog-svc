package com.sfnvm.personalblog.mapper;

import com.sfnvm.personalblog.model.entity.JpaArticle;
import com.sfnvm.personalblog.model.io.request.CreateArticleRequest;
import com.sfnvm.personalblog.model.io.response.ArticleResponse;
import com.sfnvm.personalblog.model.io.response.ArticleWithContentResponse;
import com.sfnvm.personalblog.model.projection.ArticleContentProjection;
import com.sfnvm.personalblog.model.projection.ArticleGeneralInfoProjection;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleMapper {

  ArticleResponse map(ArticleGeneralInfoProjection projection);

  ArticleWithContentResponse map(ArticleContentProjection projection);

  JpaArticle partialMap(CreateArticleRequest request);
}
