package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AnimalType implements Serializable {

    @Id
    @Column(name = "animal_type_id")
    private long id;
    private String name;
    private String thumbnail;
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "animal_type_food",
            joinColumns = @JoinColumn(name = "animal_type_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    @JoinColumn(name = "food_id", updatable = false, insertable = false)
    private List<Food> foods = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "animal_type_game",
            joinColumns = @JoinColumn(name = "animal_type_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    @JoinColumn(name = "game_id", updatable = false, insertable = false)
    private List<Game> games = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "animal_type_intervention",
            joinColumns = @JoinColumn(name = "animal_type_id"),
            inverseJoinColumns = @JoinColumn(name = "intervention_id"))
    @JoinColumn(name = "intervention_id", updatable = false, insertable = false)
    private List<Intervention> interventions = new ArrayList<>();

}
