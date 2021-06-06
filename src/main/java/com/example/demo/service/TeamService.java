package com.example.demo.service;

import com.example.demo.model.TeamEntity;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public void addTeam(TeamEntity teamEntity){
        teamRepository.save(teamEntity);
    }

    public TeamEntity findById(Integer id){
        return teamRepository.findById(id).get();
    }
}
