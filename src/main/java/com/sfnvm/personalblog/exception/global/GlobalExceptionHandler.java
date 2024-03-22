package com.sfnvm.personalblog.exception.global;

import com.sfnvm.personalblog.exception.FatalException;
import com.sfnvm.personalblog.exception.ResourceNotFoundException;
import com.sfnvm.personalblog.model.io.wrapper.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NoResourceFoundException.class)
  public BaseResponse<Void> handle(final NoResourceFoundException exception) {
    log.error(exception.getMessage(), exception);
    return BaseResponse.fail(exception.getMessage(), null);
  }

  /////////////////////////////////////
  ///// Custom Exception Handlers /////
  /////////////////////////////////////

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = FatalException.class)
  public BaseResponse<Void> handle(final FatalException exception) {
    log.error(exception.getMessage(), exception);
    return BaseResponse.fail(exception.getMessage(), null);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ResourceNotFoundException.class)
  public BaseResponse<Void> handle(final ResourceNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return BaseResponse.fail(exception.getMessage(), null);
  }
}
