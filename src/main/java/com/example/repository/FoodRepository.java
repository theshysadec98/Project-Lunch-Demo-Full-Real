package com.example.repository;
import com.example.model.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface FoodRepository extends  JpaRepository<Food, Long> {
    @Query(value = "select * from food where LOWER(name) like concat('%',LOWER(:name),'%') and LOWER(taste) like concat('%',LOWER(:taste),'%')", nativeQuery = true)
    Page<Food> findByFilter(@Param("name") String name, @Param("taste") String taste, Pageable pageable);
    @Query(value = "select * from food where LOWER(name) like concat('%',LOWER(:name),'%')", nativeQuery = true)
    Page<Food> findByFilterName(@Param("name") String name, Pageable pageable);
    @Query(value = "select * from food where LOWER(taste) like concat('%',LOWER(:taste),'%')", nativeQuery = true)
    Page<Food> findByFilterTaste(@Param("taste") String taste, Pageable pageable);
    boolean existsByName(String name);


}
