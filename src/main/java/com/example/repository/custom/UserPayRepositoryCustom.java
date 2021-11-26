package com.example.repository.custom;

import com.example.model.entity.UserPay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface UserPayRepositoryCustom {
    Page<UserPay> findAllUserByPriceWithDate(Timestamp timeStart, Timestamp timeFinal, Pageable pageable);
    Page<UserPay> findAllUserByPriceWithDateHQL(Timestamp timeStart, Timestamp timeFinal, Pageable pageable);
}
