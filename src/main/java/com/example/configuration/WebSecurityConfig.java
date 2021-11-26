package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author akitoshi
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorize -> authorize
            .antMatchers("/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/pokemon", "/v1/pokemon/{pokemonId}").hasAuthority("SCOPE_pokedex/read")
            .antMatchers(HttpMethod.POST, "/v1/pokemon").hasAuthority("SCOPE_pokedex/write")
            .antMatchers(HttpMethod.PUT, "/v1/pokemon/{pokemonId}").hasAuthority("SCOPE_pokedex/write")
            .antMatchers(HttpMethod.DELETE, "/v1/pokemon/{pokemonId}").hasAuthority("SCOPE_pokedex/write")
            .anyRequest().denyAll()
        )
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .csrf().disable()
        .cors();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedHeaders("*")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD");
      }
    };
  }
}
