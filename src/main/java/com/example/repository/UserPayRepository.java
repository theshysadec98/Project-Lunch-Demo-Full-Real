package com.example.repository;

import com.example.model.dto.Response.UserPayResponse;

import com.example.model.entity.User;
import com.example.repository.custom.UserPayRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserPayRepository extends JpaRepository<User, Long> , UserPayRepositoryCustom {
    @Query(value = "SELECT u.name, SUM(f.price)" +
            " FROM users u INNER JOIN booking bk ON u.id = bk.user_id" +
            " INNER JOIN booking_food bk_f ON bk.id = bk_f.booking_id" +
            " INNER JOIN food f ON bk_f.food_id = f.id"+
            " WHERE  bk.created_date >= :time_before AND bk.created_date <= :time_after"+
            " GROUP BY u.id",  nativeQuery = true)
    Page<UserPayResponse> getAllUserByPrice(@Param("time_before") String timeBefore, @Param("time_after") String timeAfter, Pageable pageable);


}
