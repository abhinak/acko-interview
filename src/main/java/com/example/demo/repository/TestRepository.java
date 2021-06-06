package com.example.demo.repository;

import com.example.demo.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {

    public TestEntity findByName(String name);
}
