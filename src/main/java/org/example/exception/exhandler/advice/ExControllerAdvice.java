package org.example.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.exception.UserException;
import org.example.exception.exhandler.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "org.example.exception.api")
public class ExControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorResult illegalExceptionHandler(IllegalArgumentException e) {
    log.error("[exceptionHandler] ex", e);
    return new ErrorResult("BAD", e.getMessage());
  }

  //파라미터에 있다면 (UserException.class) 생략 가능
  @ExceptionHandler
  public ResponseEntity<ErrorResult> userExceptionHandler(UserException e) {
    log.error("[exceptionHandler] ex", e);
    ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
    return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler
  public ErrorResult exceptionHandler(Exception e) {
    log.error("[exceptionHandler] ex", e);
    return new ErrorResult("EX", "내부 오류");
  }

}
