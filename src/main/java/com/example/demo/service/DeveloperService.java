package com.example.demo.service;

import com.example.demo.model.DeveloperEntity;
import com.example.demo.model.TeamEntity;
import com.example.demo.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    DeveloperRepository developerRepository;

    public void addDeveloper(DeveloperEntity developerEntity){
        developerRepository.save(developerEntity);
    }

    public List<DeveloperEntity> findAllByTeamEntity(TeamEntity teamEntity){
        return developerRepository.findByTeamEntity(teamEntity);
    }
}
