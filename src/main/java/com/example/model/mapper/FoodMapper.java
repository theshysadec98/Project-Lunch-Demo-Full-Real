package com.example.model.mapper;

import com.example.model.dto.Requests.FoodRequest;
import com.example.model.dto.Response.FoodResponse;
import com.example.model.entity.Food;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodMapper {
    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);
    FoodRequest foodToRequest(Food food);
    Food requestToFood(FoodRequest foodRequest);
    FoodResponse foodToResponse(Food food);
    Food responseToFood(FoodResponse foodResponse);

    FoodResponse requestToResponse(FoodRequest foodRequest);
}
