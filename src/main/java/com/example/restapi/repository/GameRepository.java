package com.example.restapi.repository;

import com.example.restapi.model.Game;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("Select p From Game p")
    List<Game> findAllGames(Pageable page);

}
