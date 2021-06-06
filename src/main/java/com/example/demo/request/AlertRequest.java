package com.example.demo.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class AlertRequest {

    public AlertRequest(String phoneNumber, String message){
        this.phoneNumber = phoneNumber;
        this.message= message;
    }

    private String phoneNumber;
    private String message;
}
