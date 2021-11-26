package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public class ErrorException extends RuntimeException {

  private Error error;
  private HttpStatus status;
}
