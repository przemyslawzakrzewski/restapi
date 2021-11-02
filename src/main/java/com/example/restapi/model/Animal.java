package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Animal implements Serializable {

    @Id
    @Column(name = "animal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "animal_type_id")
    private long animalTypeId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "animal_type_id", updatable = false, insertable = false)
    private AnimalType animalType;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id", updatable = false, insertable = false)
    private Owner owner;
    private Integer age;
    private Integer weight;
    private Integer food;
    private Integer entertainment;
    private Integer health;

}
