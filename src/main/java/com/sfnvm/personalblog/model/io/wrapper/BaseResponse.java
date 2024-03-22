package com.sfnvm.personalblog.model.io.wrapper;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;

/**
 * The type Base response.
 *
 * @param <T> the type parameter
 */
@Getter
@Setter(AccessLevel.MODULE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseResponse<T> {

  private T data;

  private Metadata metadata;

  /**
   * Single base response.
   *
   * @param <E>    the type parameter
   * @param result the result
   * @return the base response
   */
  public static <E> BaseResponse<E> of(final Result<E> result) {
    val response = new BaseResponse<E>();
    response.data = result.getData();
    return response;
  }

  /**
   * Single base response.
   *
   * @param <T>    the type parameter
   * @param result the result
   * @return the base response
   */
  public static <T> BaseResponse<T> of(T result) {
    val response = new BaseResponse<T>();
    response.data = result;
    return response;
  }

  /**
   * Fail base response.
   *
   * @param errors the errors
   * @return the base response
   */
  public static BaseResponse<Void> fail(final List<FieldViolation> errors) {
    return fail(null, errors);
  }

  /**
   * Fail base response.
   *
   * @param message the message
   * @param errors  the errors
   * @return the base response
   */
  public static BaseResponse<Void> fail(final String message, final List<FieldViolation> errors) {
    val response = new BaseResponse<Void>();
    response.metadata = new Metadata();
    response.metadata.setMessage(message);
    response.metadata.setErrors((errors != null) ? new ArrayList<>(errors) : null);
    return response;
  }
}
