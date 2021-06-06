package com.example.demo.util;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class CommonnUtil {

    public<T> ResponseEntity postCall (String url, HttpEntity<T> request, Class<T> tClass ){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(url, request, tClass);
    }
}
