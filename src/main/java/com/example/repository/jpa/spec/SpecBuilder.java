package com.example.repository.jpa.spec;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface SpecBuilder<T> {

  Pageable getPageable();

  Specification<T> build();

  Specification<T> buildOr();

}
