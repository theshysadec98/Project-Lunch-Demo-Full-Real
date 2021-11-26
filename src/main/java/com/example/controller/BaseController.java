package com.example.controller;

import com.example.exception.Error;
import com.example.exception.Error.CodeEnum;
import com.example.exception.ErrorException;
import com.example.exception.ExceptionUtils;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author akitoshi
 */
@Slf4j
@RestControllerAdvice
public class BaseController implements ErrorController {

  @ExceptionHandler({ErrorException.class})
  public ResponseEntity<Error> handleErrorException(ErrorException exception) {
    log.warn("Caught a error exception request {}", exception.getError().getCode());
    log.debug("Caught a error exception request", exception);
    return new ResponseEntity<>(
        exception.getError(),
        exception.getStatus()
    );
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Error> handleException(Exception exception) {
    return new ResponseEntity<>(
        Error.builder()
            .messages(Collections.singletonList("Unexpected error"))
            .code(CodeEnum.UNEXPECTED_ERROR)

            .build(),
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

}

