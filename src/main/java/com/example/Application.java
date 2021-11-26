package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author akitoshi
 */
@SpringBootApplication

public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @PostConstruct
  public void init(){
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}

