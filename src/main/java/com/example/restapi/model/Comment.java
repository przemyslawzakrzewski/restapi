package com.example.restapi.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    private long id;
    private long postId;
    private String content;
    private LocalDateTime created;

}
