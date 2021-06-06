package com.example.demo.request;

import com.example.demo.model.DeveloperEntity;
import com.example.demo.model.TeamEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class TeamDeveloperRequest {

    private TeamEntity teamEntity;
    private List<DeveloperEntity> developers;
}
