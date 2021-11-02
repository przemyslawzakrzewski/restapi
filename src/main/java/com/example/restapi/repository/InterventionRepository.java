package com.example.restapi.repository;

import com.example.restapi.model.Intervention;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {

    @Query("Select p From Intervention p")
    List<Intervention> findAllInterventions(Pageable page);

}
