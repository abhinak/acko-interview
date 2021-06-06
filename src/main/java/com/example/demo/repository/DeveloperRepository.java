package com.example.demo.repository;

import com.example.demo.model.DeveloperEntity;
import com.example.demo.model.TeamEntity;
import com.example.demo.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Integer> {

    List<DeveloperEntity> findByTeamEntity(TeamEntity teamEntity);
}
