package com.example.restapi.repository;

import com.example.restapi.model.Food;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("Select p From Food p")
    List<Food> findAllFoods(Pageable page);

}
