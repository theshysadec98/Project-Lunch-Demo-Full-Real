package com.example.model.dto.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePage {

    private Object responseData;
    private Integer totalPage;
    private Long totalRecord;

    public ResponsePage(Object responseData, Integer totalPage, Long totalRecord) {
        this.responseData = responseData;
        this.totalPage = totalPage;
        this.totalRecord = totalRecord;
    }
}