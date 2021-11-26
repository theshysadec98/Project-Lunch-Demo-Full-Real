package com.example.service.impl;

import com.example.exception.Error;
import com.example.exception.ErrorException;
import com.example.model.dto.Response.ResponsePage;
import com.example.model.dto.Response.ResponseWrapper;
import com.example.model.dto.Response.UserPayResponse;
import com.example.model.entity.UserPay;
import com.example.model.mapper.UserPayMapper;
import com.example.repository.UserPayRepository;
import com.example.repository.impl.UserPayRepositoryImpl;
import com.example.service.UserPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class UserPayServiceImpl implements UserPayService {
    @Autowired
    private UserPayRepository userPayRepository;

    @Override
    public ResponsePage findAllUserByPriceWithDate(Long timeStart, Long timeFinal, Pageable pageable){
        Page<UserPay> userPayPage = userPayRepository.findAllUserByPriceWithDate(new Timestamp(timeStart), new Timestamp(timeFinal), pageable);
        Page<UserPayResponse> responsePage = userPayPage.map(UserPayMapper.INSTANCE::entityToResponse);
        return new ResponsePage(responsePage, responsePage.getTotalPages(),  responsePage.getTotalElements());
    }

    @Override
    public ResponsePage findAllUserByPriceWithDateHQL(Long timeStart, Long timeFinal, Pageable pageable) {
        Page<UserPay> userPayPage = userPayRepository.findAllUserByPriceWithDateHQL(new Timestamp(timeStart), new Timestamp(timeFinal), pageable);
        Page<UserPayResponse> responsePage = userPayPage.map(UserPayMapper.INSTANCE::entityToResponse);
        return new ResponsePage(responsePage, responsePage.getTotalPages(),  responsePage.getTotalElements());
    }
}
