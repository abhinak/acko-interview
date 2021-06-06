package com.example.demo.service;

import com.example.demo.model.TestEntity;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public TestEntity findByName(String name){
        return testRepository.findByName(name);
    }

    public TestEntity findById(Integer id){
        Optional<TestEntity> optionalTestEntity =testRepository.findById(id);
        return optionalTestEntity.get();
    }

    public void addTest(TestEntity testEntity){
        testRepository.save(testEntity);
    }
}
