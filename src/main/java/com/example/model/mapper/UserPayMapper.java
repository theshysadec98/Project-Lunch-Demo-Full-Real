package com.example.model.mapper;

import com.example.model.dto.Response.UserPayResponse;
import com.example.model.entity.UserPay;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserPayMapper {
    UserPayMapper INSTANCE = Mappers.getMapper(UserPayMapper.class);

    UserPay responseToEntity(UserPayResponse response);
    UserPayResponse entityToResponse(UserPay userPay);
    List<UserPayResponse> listResponse(List<UserPay> payList);
}
