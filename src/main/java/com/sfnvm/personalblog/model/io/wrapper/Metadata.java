package com.sfnvm.personalblog.model.io.wrapper;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Metadata.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Metadata {

  private Integer page;
  private Integer totalPage;
  private Boolean hasNextPage;
  private Boolean hasPreviousPage;
  private Integer size;
  private Long totalElements;

  @Builder.Default
  private List<FieldViolation> errors = new ArrayList<>();

  private String message;
  private String field;
}
