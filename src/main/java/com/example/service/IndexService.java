package com.example.service;

import com.example.service.impl.ContextService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;

/**
 * @author akitoshi
 */
@Service
@Slf4j
public class IndexService extends ContextService {

  public Map<String, Object> getTokenInfo() {
    try {
      return super.jwtAttributes();
    } catch (InvalidBearerTokenException e) {
      return Map.of("user", "anonymous");
    }
  }
}

