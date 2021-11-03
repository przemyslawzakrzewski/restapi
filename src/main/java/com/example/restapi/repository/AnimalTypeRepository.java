package com.example.restapi.repository;

import com.example.restapi.model.AnimalType;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {

    @Query("Select p From AnimalType p")
    List<AnimalType> findAllAnimalTypes(Pageable page);

}
