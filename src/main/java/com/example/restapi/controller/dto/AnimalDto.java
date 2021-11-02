package com.example.restapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnimalDto {

    private long id;
    private Long ownerId;
    private long animalTypeId;
    private String name;
    private Integer age;
    private Integer weight;
    private Integer food;
    private Integer entertainment;
    private Integer health;

}
