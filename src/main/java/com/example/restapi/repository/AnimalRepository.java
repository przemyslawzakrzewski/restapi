package com.example.restapi.repository;

import com.example.restapi.model.Animal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("Select p From Animal p")
    List<Animal> findAllAnimals(Pageable page);

}
