package com.sfnvm.personalblog.service;

import com.sfnvm.personalblog.config.language.I18n;
import com.sfnvm.personalblog.exception.ResourceNotFoundException;
import com.sfnvm.personalblog.mapper.ArticleMapper;
import com.sfnvm.personalblog.model.io.request.CreateArticleRequest;
import com.sfnvm.personalblog.model.io.response.ArticleResponse;
import com.sfnvm.personalblog.model.io.response.ArticleWithContentResponse;
import com.sfnvm.personalblog.model.io.response.IdentityResponse;
import com.sfnvm.personalblog.model.projection.ArticleContentProjection;
import com.sfnvm.personalblog.model.projection.ArticleGeneralInfoProjection;
import com.sfnvm.personalblog.repository.ArticleRepository;
import com.sfnvm.personalblog.util.FrontMatterUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleService {

  private final ArticleRepository repository;

  private final I18n i18n;

  private final ArticleMapper mapper = Mappers.getMapper(ArticleMapper.class);

  public ArticleResponse findById(Long id) {
    val rs = repository.findById(id, ArticleGeneralInfoProjection.class);

    if (rs.isEmpty()) {
      throw new ResourceNotFoundException(i18n.get("error.entity.not-found", id));
    }

    return mapper.map(rs.get());
  }

  public ArticleWithContentResponse findAndExtractContentById(Long id) {
    val rs = repository.findById(id, ArticleContentProjection.class);

    if (rs.isEmpty()) {
      throw new ResourceNotFoundException(i18n.get("error.entity.not-found", id));
    }

    return mapper.map(rs.get());
  }

  public List<ArticleResponse> findAll() {
    return repository.findBy(ArticleGeneralInfoProjection.class).stream()
        .map(mapper::map)
        .collect(Collectors.toList());
  }

  public IdentityResponse createArticle(CreateArticleRequest request) {
    val toSave = mapper.partialMap(request);
    val frontMatter = FrontMatterUtil.extractAndBuild(request.getContent());

    toSave.setTitle(frontMatter.getTitle());
    toSave.setPublishedDate(frontMatter.getPublishedDate());

    return IdentityResponse.builder()
        .id(repository.save(toSave).getId())
        .build();
  }
}
