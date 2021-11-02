package com.example.restapi.repository;

import com.example.restapi.model.Owner;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("Select p From Owner p")
    List<Owner> findAllOwners(Pageable page);

}
