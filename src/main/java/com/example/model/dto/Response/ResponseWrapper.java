package com.example.model.dto.Response;

import com.example.model.dto.Response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper {

    private String responseErrorCode;
    private String responseMessage;
    private Object responseData;

    public ResponseWrapper(Response response, Object data) {
        this.responseErrorCode = response.getErrorCode();
        this.responseMessage = response.getResponseMessage();
        this.responseData = data;
    }

}