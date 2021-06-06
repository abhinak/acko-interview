package com.example.demo.controller;

import com.example.demo.model.TestEntity;
import com.example.demo.service.TestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/v1")
@RestController
public class TestController {

    @Autowired
    TestService testService;

    private static Map<String,String> response= new HashMap<>();

    @GetMapping(value = "/test/{id}")
    public ResponseEntity<TestEntity> getTestUser(@PathVariable Integer id){
        TestEntity testEntity = testService.findById(id);
        return new ResponseEntity<>(testEntity, HttpStatus.OK);
    }

    @PostMapping(value = "test")
    public ResponseEntity<Map> addTest(@RequestBody TestEntity testEntity){
        testService.addTest(testEntity);
        response.put("status","SUCCESS");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
