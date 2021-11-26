package com.example.service;

import com.example.model.dto.Requests.FoodRequest;
import com.example.model.dto.Response.FoodResponse;
import com.example.model.dto.Response.ResponsePage;


import org.springframework.data.domain.Pageable;

public interface FoodService{
    FoodResponse create(FoodRequest foodRequest);
    FoodResponse findById(Long id);
    void remove(Long id);
    ResponsePage findByFilter(String name, String taste, Pageable pageable);
    FoodResponse update(FoodRequest foodRequest);
}
