package com.example.model.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Response {
    SUCCESS("0", "success"),
    ERROR("1", "error"),
    FOOD("2", "Value of food Null");
    private String errorCode;
    private String responseMessage;
}
