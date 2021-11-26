package com.example.repository.jpa.spec.builder;

import com.example.model.entity.User;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author akitoshi
 */
public class UserSpecBuilder extends AbstractSpecBuilder<User> {

  public UserSpecBuilder pageable(Integer page, Integer size, List<String> orders) {
    super.buildPageable(page, size, orders);
    return this;
  }

  public UserSpecBuilder specs(List<Specification<User>> specs) {
    super.buildSpecs(specs);
    return this;
  }

  public UserSpecBuilder nameMatch(String nameMatch) {
    if (StringUtils.isNotBlank(nameMatch)) {
      specs.add((root, query, criteriaBuilder) ->
          criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), "%" + nameMatch + "%")
      );
    }
    return this;
  }

  public UserSpecBuilder type(String type) {
    if (StringUtils.isNotBlank(type)) {
      specs.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }
    return this;
  }

}
