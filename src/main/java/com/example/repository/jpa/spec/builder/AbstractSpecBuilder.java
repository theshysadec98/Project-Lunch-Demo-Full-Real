package com.example.repository.jpa.spec.builder;

import com.example.repository.jpa.spec.SpecBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;


public abstract class AbstractSpecBuilder<T> implements SpecBuilder<T> {

  private final static int DEFAULT_PAGE_NUMBER = 0;
  private final static int DEFAULT_PAGE_SIZE = 20;

  @Getter
  protected Pageable pageable;
  protected final List<Specification<T>> specs = new ArrayList<>();

  @Override
  public Specification<T> build() {
    return getSpecWithRelation(false);
  }

  @Override
  public Specification<T> buildOr() {
    return getSpecWithRelation(true);
  }

  protected void buildPageable(Integer page, Integer size, List<String> orders) {
    page = page == null || page < 0 ? DEFAULT_PAGE_NUMBER : page;
    size = size == null || size <= 0 ? DEFAULT_PAGE_SIZE : size;
    this.pageable = PageRequest.of(page, size, Sort.by(parseOrders(orders)));
  }

  protected void buildSpecs(List<Specification<T>> specs) {
    this.specs.addAll(specs);
  }

  private Specification<T> getSpecWithRelation(boolean relationOr) {
    if (CollectionUtils.isEmpty(specs)) {
      return Specification.where(null);
    }
    Specification<T> spec = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      if (specs.get(i) != null) {
        spec = relationOr ? spec.or(specs.get(i)) : spec.and(specs.get(i));
      }
    }
    return spec;
  }

  private List<Order> parseOrders(List<String> strOrders) {
    if (CollectionUtils.isEmpty(strOrders)) {
      return Collections.emptyList();
    }
    return strOrders.stream()
        .filter(StringUtils::isNotBlank)
        .map(strOrder -> {
          String[] elements = strOrder.trim().split(" ");
          String field = elements[0];
          Direction direction = elements.length < 2 ? Direction.ASC : Direction.fromString(elements[1]);
          return direction.isDescending() ? Order.desc(field) : Order.asc(field);
        })
        .collect(Collectors.toList());
  }

}
