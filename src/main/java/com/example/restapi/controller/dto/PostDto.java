package com.example.restapi.controller.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDto {

    private long id;
    private String title;
    private String content;
    private LocalDateTime created;

}
