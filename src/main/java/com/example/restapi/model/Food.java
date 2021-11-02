package com.example.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Food implements Serializable {

    @Id
    @Column(name = "food_id")
    private long id;
    private String name;
    private Integer value;

}
