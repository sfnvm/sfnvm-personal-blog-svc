package com.sfnvm.personalblog.repository;

import com.sfnvm.personalblog.model.entity.JpaArticle;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<JpaArticle, Long> {

  /**
   * <b>Dynamic Projection</b>
   */
  <T> Optional<T> findById(Long id, Class<T> clazz);

  /**
   * <b>Dynamic Projection</b>
   */
  <T> Collection<T> findBy(Class<T> clazz);
}
