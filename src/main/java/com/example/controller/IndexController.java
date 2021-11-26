package com.example.controller;

import com.example.service.IndexService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akitoshi
 */
@RestController
public class IndexController extends BaseController {

  @Autowired
  private IndexService indexService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<String> index() {
    return new ResponseEntity<>(
        "Hello world!!!",
        HttpStatus.OK
    );
  }

  @GetMapping("/tokenInfo")
  public ResponseEntity<Map<String, Object>> tokenInfo() {
    return new ResponseEntity<>(
        indexService.getTokenInfo(),
        HttpStatus.OK
    );
  }
}
