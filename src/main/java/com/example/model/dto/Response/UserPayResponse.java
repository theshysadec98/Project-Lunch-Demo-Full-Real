package com.example.model.dto.Response;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserPayResponse {
    private String name;
    private Long price;
}
