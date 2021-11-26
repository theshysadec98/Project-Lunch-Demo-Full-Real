package com.example.service;

import com.example.model.dto.Response.ResponsePage;
import com.example.model.dto.Response.ResponseWrapper;
import org.springframework.data.domain.Pageable;


public interface UserPayService {
    ResponsePage findAllUserByPriceWithDate(Long timeStart, Long timeFinal, Pageable pageable);
    ResponsePage findAllUserByPriceWithDateHQL(Long timeStart, Long timeFinal, Pageable pageable);
}
