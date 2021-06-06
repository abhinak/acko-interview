package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "alerts")
@Data
public class AlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "developerId")
    private DeveloperEntity developerEntity;

    private String message;
}
