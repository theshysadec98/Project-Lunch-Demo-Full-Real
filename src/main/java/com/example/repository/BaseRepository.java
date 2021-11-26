package com.example.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;

public abstract class BaseRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    protected void appendSortQuery(Pageable pageable, StringBuilder sql) {
        if (pageable.getSort() != null) {
            Sort sort = pageable.getSort();
            for (Iterator i = sort.iterator(); i.hasNext(); ) {
                Sort.Order order = (Sort.Order) i.next();
                sql.append(String.format(" ORDER BY %s %s", order.getProperty(), order.getDirection().isAscending() ? "ASC" : "DESC"));
                if (i.hasNext()) {
                    sql.append(", ");
                }
            }
        }
    }

    protected void appendPageQuery(Pageable pageable, Query query) {
        int page = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
    }
}
