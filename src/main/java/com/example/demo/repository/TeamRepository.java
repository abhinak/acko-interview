package com.example.demo.repository;

import com.example.demo.model.TeamEntity;
import com.example.demo.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

}
