package com.example.model.dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodResponse {
    private Long id;
    private String name;
    private String taste;
    private String description;
    private Long quantity;
    private Long price;
}
