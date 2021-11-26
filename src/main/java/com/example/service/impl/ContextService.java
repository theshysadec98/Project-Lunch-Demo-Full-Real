package com.example.service.impl;

import com.example.exception.Error;
import com.example.exception.Error.CodeEnum;
import com.example.exception.ErrorException;
import java.util.Collections;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * @author chautn
 */
public abstract class ContextService {

  protected Map<String, Object> jwtAttributes() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof JwtAuthenticationToken) {
      var jwtAuthentication = (JwtAuthenticationToken) authentication;
      return jwtAuthentication.getTokenAttributes();
    }
    throw new ErrorException(
        Error.builder()
            .messages(Collections.singletonList("Invalid access token"))
            .code(CodeEnum.UNAUTHORIZED)
            .build(),
        HttpStatus.UNAUTHORIZED
    );
  }

  protected String sub() {
    return (String) jwtAttributes().get("sub");
  }
}
