package com.example.model.dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
    private Long id;
    private String name;
    private String taste;
    private String description;
    private Long quantity;
    private Long price;
}
