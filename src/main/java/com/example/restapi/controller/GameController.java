package com.example.restapi.controller;

import com.example.restapi.model.Game;
import com.example.restapi.service.GameService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/games")
    public List<Game> getGames(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return gameService.getGames(pageNumber, sortDirection);
    }

    @GetMapping("/games/{id}")
    public Game getSingleGame(@PathVariable long id) {
        return gameService.getSingleGame(id);
    }

    @PostMapping("/games")
    public Game addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @PutMapping("/games")
    public Game editGame(@RequestBody Game game) {
        return gameService.editGame(game);
    }

    @DeleteMapping("/games/{id}")
    public void deleteGame(@PathVariable long id) {
        gameService.deleteGame(id);
    }

}
