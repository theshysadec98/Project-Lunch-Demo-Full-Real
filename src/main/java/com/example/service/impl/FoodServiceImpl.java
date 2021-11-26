package com.example.service.impl;
import com.example.common.CheckEmpty;
import com.example.exception.Error;
import com.example.exception.ErrorException;
import com.example.model.dto.Requests.FoodRequest;
import com.example.model.dto.Response.FoodResponse;
import com.example.model.dto.Response.ResponsePage;
import com.example.model.dto.Response.ResponseWrapper;
import com.example.model.entity.Food;
import com.example.model.mapper.FoodMapper;
import com.example.repository.FoodRepository;
import com.example.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;


@Component
public class FoodServiceImpl  implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public FoodResponse findById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(()->new ErrorException(Error.builder()
                        .code(Error.CodeEnum.NOT_FOUND)
                        .messages(Collections.singletonList("Food with id " + id + " not found!"))
                        .build(), HttpStatus.BAD_REQUEST));
        return FoodMapper.INSTANCE.foodToResponse(food);
    }

    @Override
    public FoodResponse create(FoodRequest foodRequest) {
        if(foodRepository.existsByName(foodRequest.getName())) {
            throw new ErrorException(Error.builder()
                    .code(Error.CodeEnum.BAD_REQUEST)
                    .messages(Collections.singletonList("Duplicate name"))
                    .build(), HttpStatus.BAD_REQUEST);
        }else {
            foodRepository.save(FoodMapper.INSTANCE.requestToFood(foodRequest));

        }
        return FoodMapper.INSTANCE.requestToResponse(foodRequest);
    }

    @Override
    public void remove(Long id) {
        Food food = FoodMapper.INSTANCE.responseToFood(findById(id));
        if(CheckEmpty.standardInput(food.getName())) throw new ErrorException(Error.builder()
                .code(Error.CodeEnum.NOT_FOUND)
                .messages(Collections.singletonList("id does not exist in data"))
                .build(), HttpStatus.NOT_FOUND);
        foodRepository.deleteById(id);
    }

    @Override
    public ResponsePage findByFilter(String name, String taste, Pageable pageable) {
        Page<Food> foodPage = foodRepository.findByFilter(name, taste, pageable);
        if(CheckEmpty.standardInput(name)) foodPage = foodRepository.findByFilterTaste(taste, pageable);
        if(CheckEmpty.standardInput(taste)) foodPage =  foodRepository.findByFilterName(name, pageable);
        if(CheckEmpty.standardInput(name) && CheckEmpty.standardInput(taste) ) foodPage =  foodRepository.findAll(pageable);
        Page<FoodResponse> foodResponses = foodPage.map(food ->
                FoodResponse.builder()
                        .id(food.getId())
                        .name(food.getName())
                        .taste(food.getTaste())
                        .description(food.getDescription())
                        .quantity(food.getQuantity())
                        .price(food.getPrice())
                        .build());
        return new ResponsePage(foodResponses, foodResponses.getTotalPages(), foodResponses.getTotalElements());
    }


    @Override
    public FoodResponse update(FoodRequest foodRequest){
        Food foodUpdate = foodRepository.getById(foodRequest.getId());
        if(CheckEmpty.standardInput(foodRequest.getName()))
            throw new ErrorException(Error.builder()
                    .messages(Collections.singletonList("Name null"))
                    .build(), HttpStatus.NO_CONTENT);
        if(CheckEmpty.standardInput(foodRequest.getName()))
            throw new ErrorException(Error.builder()
                    .messages(Collections.singletonList("Duplicate name"))
                    .build(), HttpStatus.BAD_REQUEST);
        foodUpdate.setName(foodRequest.getName());
        foodUpdate.setTaste(foodRequest.getTaste());
        foodUpdate.setDescription(foodRequest.getDescription());
        foodRepository.save(foodUpdate);
        return FoodMapper.INSTANCE.requestToResponse(foodRequest);
    }


}