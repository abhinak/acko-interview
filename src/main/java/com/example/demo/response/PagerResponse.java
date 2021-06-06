package com.example.demo.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class PagerResponse {

    public PagerResponse(boolean isSuccess){
        this.isSuccess=isSuccess;
    }

    public PagerResponse(boolean isSuccess, String errorMessage){
        this.isSuccess=isSuccess;
        this.errorMessage=errorMessage;
    }

    private boolean isSuccess;
    private String errorMessage;
}
