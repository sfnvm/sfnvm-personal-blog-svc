package com.sfnvm.personalblog.model.io.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

/**
 * The type Result.
 *
 * @param <T> the type parameter
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

  private final T data;
  private final Object[] objects;

  /**
   * Of result.
   *
   * @param <T>  the type parameter
   * @param data the data
   * @return the result
   */
  public static <T> Result<T> of(final T data) {
    return new Result<>(data, null);
  }

  /**
   * Of result.
   *
   * @param <T>  the type parameter
   * @param data the data
   * @return the result
   */
  public static <T> Result<T> of(final T data, final Object[] objects) {
    return new Result<>(data, objects);
  }

  public static <T> Result<T> empty() {
    return new Result<>(null, null);
  }

  /**
   * Map result.
   *
   * @param <E>    the type parameter
   * @param mapper the mapper
   * @return the result
   */
  public <E> Result<E> map(final Function<T, E> mapper) {
    if (isEmpty()) {
      return empty();
    }
    val newContent = mapper.apply(data);
    return of(newContent, objects);
  }

  public boolean isEmpty() {
    return data == null;
  }
}
