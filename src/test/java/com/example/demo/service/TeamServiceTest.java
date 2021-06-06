package com.example.demo.service;

import com.example.demo.model.TeamEntity;
import com.example.demo.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    TeamService teamService;

    TeamRepository teamRepository;
    TeamEntity teamEntity;

    Optional<TeamEntity> optionalTeamEntity;

    @Test
    public void addTeamTest(){
        Mockito.doNothing().when(teamRepository).save(Mockito.any());
        teamService.addTeam(teamEntity);
        Mockito.verify(teamRepository,Mockito.times(1)).save(teamEntity);

    }

    @Test
    public void findByIdTest(){
        when(teamRepository.findById(any())).thenReturn(optionalTeamEntity);
        TeamEntity teamEntity = teamService.findById(1);
        assertEquals(teamEntity.getName(),optionalTeamEntity.get().getName());
        assertEquals(teamEntity.getId(),optionalTeamEntity.get().getId());
    }

    @Test
    public void findByIdFails(){
        when(teamRepository.findById(1)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> teamService.findById(1));
    }

    @BeforeEach
    public void setUp(){
        teamRepository = Mockito.mock(TeamRepository.class);
        teamEntity = new TeamEntity();
        teamEntity.setId(1);
        teamEntity.setName("myTeam");
        optionalTeamEntity = Optional.of(teamEntity);
    }
}
