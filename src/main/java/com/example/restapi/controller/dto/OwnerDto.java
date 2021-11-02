package com.example.restapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerDto {

    private long id;
    private String first_name;
    private String last_name;
    private String mobile;
    private String email;
    private String notes;

}
