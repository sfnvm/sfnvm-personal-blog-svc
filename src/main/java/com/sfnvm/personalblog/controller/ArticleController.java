package com.sfnvm.personalblog.controller;

import com.sfnvm.personalblog.model.io.request.CreateArticleRequest;
import com.sfnvm.personalblog.model.io.response.ArticleResponse;
import com.sfnvm.personalblog.model.io.response.IdentityResponse;
import com.sfnvm.personalblog.model.io.wrapper.BaseResponse;
import com.sfnvm.personalblog.service.ArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/public/articles")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("{id}")
  public ResponseEntity<BaseResponse<ArticleResponse>> findArticleById(@PathVariable long id) {
    return ResponseEntity.ok(BaseResponse.of(
        articleService.findById(id)
    ));
  }

  @GetMapping("{id}/content")
  public ResponseEntity<String> findArticleContentById(@PathVariable long id) {
    return ResponseEntity.ok(
        articleService.findAndExtractContentById(id).getContent()
    );
  }

  @GetMapping
  public ResponseEntity<BaseResponse<List<ArticleResponse>>> find() {
    return ResponseEntity.ok(BaseResponse.of(articleService.findAll()));
  }

  @PostMapping
  public ResponseEntity<BaseResponse<IdentityResponse>> createArticle(@RequestBody CreateArticleRequest request) {
    return ResponseEntity.ok(BaseResponse.of(articleService.createArticle(request)));
  }
}
