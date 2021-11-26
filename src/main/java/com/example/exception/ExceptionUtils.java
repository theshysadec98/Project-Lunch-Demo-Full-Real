package com.example.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ExceptionUtils {

  public static List<String> stackTraceToListMessages(StackTraceElement[] elements) {
    return elements != null ?
        Arrays.stream(elements).map(StackTraceElement::toString).collect(Collectors.toList()) :
        Collections.emptyList();
  }
}
