package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PagerDutyService {

    public Integer getRandomNumber(Integer size){
        Random rand = new Random();
        return rand.nextInt((size) + 1) ;
    }
}
