package com.example.demo.controller;

import com.example.demo.model.AlertEntity;
import com.example.demo.model.DeveloperEntity;
import com.example.demo.model.TeamEntity;
import com.example.demo.request.AlertRequest;
import com.example.demo.request.TeamDeveloperRequest;
import com.example.demo.response.PagerResponse;
import com.example.demo.service.DeveloperService;
import com.example.demo.service.PagerDutyService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RequestMapping("/v1")
@RestController
public class PagerDutyController {

    @Autowired
    DeveloperService developerService;

    @Autowired
    TeamService teamService;


    @Autowired
    PagerDutyService pagerDutyService;


    public static final String ALERT_URL = "http://notif-service-dummy.acko.com/sms";

    private HttpHeaders headers = new HttpHeaders();



    @PostMapping(value = "/developer")
    public ResponseEntity<PagerResponse> addDevelopers(@RequestBody TeamDeveloperRequest developerRequest){
        TeamEntity teamEntity = developerRequest.getTeamEntity();
        PagerResponse  addDeveloperResponse;

        List<DeveloperEntity> developers = developerRequest.getDevelopers();
        if (developers.size()==0){
            addDeveloperResponse = new PagerResponse(false, "Input Validation !! The team does not have ay developer");
            return new ResponseEntity<>(addDeveloperResponse, HttpStatus.BAD_REQUEST);
        }

        try{
            teamService.addTeam(teamEntity);
            for (DeveloperEntity developerEntity: developers){
                developerService.addDeveloper(developerEntity);
            }
            addDeveloperResponse = new PagerResponse(true);
        }
        catch (Exception e){
            addDeveloperResponse = new PagerResponse(false, e.getMessage());
            return new ResponseEntity<>(addDeveloperResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(addDeveloperResponse, HttpStatus.OK);
    }

    @PostMapping(value = "alerts/{teamId}")
    public ResponseEntity<PagerResponse> sendAlert(@PathVariable Integer teamId){
        TeamEntity teamEntity = teamService.findById(teamId);

        PagerResponse pagerResponse;

        if (Objects.isNull(teamEntity)){
            pagerResponse = new PagerResponse(false,"Input Validation!! The team does not exist");
            return new ResponseEntity<>(pagerResponse, HttpStatus.BAD_REQUEST);
        }

        List<DeveloperEntity> developerEntities = developerService.findAllByTeamEntity(teamEntity);

        if (developerEntities.size()==0){
            pagerResponse = new PagerResponse(false, "This team does not have any developers");
            return new ResponseEntity<>(pagerResponse, HttpStatus.BAD_REQUEST);
        }

        Integer developerNNumber = pagerDutyService.getRandomNumber(developerEntities.size());
        DeveloperEntity developerTobeAlerted = developerEntities.get(developerNNumber);

        AlertRequest alertRequest = new AlertRequest(developerTobeAlerted.getPhoneNumber(),"HarCoded Message");
        HttpEntity<AlertRequest> request = new HttpEntity<>(alertRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PagerResponse> alertResponse = restTemplate.postForEntity(ALERT_URL, request,PagerResponse.class);
        return alertResponse;
    }

}
