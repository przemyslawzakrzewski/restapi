package com.example.restapi.service;

import com.example.restapi.model.Game;
import com.example.restapi.repository.GameRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private static final int PAGE_SIZE = 20;
    private final GameRepository gameRepository;
    private static final Logger LOGGER = LogManager.getLogger(GameService.class);

    @Cacheable(value = "games", key = "#p0")
    public List<Game> getGames(int page, Sort.Direction sort) {
        return gameRepository.findAllGames(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Cacheable(value = "singleGame", key = "#id")
    public Game getSingleGame(long id) {
        Optional<Game> singleGame = gameRepository.findById(id);
        if (singleGame.isPresent()) {
            LOGGER.info("Game fetched.");
            return singleGame.get();
        } else {
            LOGGER.info("No single game found.");
            return null;
        }
    }

    public Game addGame(Game game) {
        LOGGER.info("Game added.");
        return gameRepository.save(game);
    }

    @Transactional
    @CachePut(value = "editGame")
    public Game editGame(Game game) {
        Optional<Game> gameToEdit = gameRepository.findById(game.getId());
        if (gameToEdit.isPresent()) {
            LOGGER.info("Game edited.");
            Game gameEdited = gameToEdit.get();
            gameEdited.setName(game.getName());
            gameEdited.setValue(game.getValue());
            return gameRepository.save(gameEdited);
        } else {
            LOGGER.info("No game found to be edited.");
            return null;
        }
    }

    @Transactional
    @CacheEvict(value = "deleteGame")
    public void deleteGame(long id) {
        Optional<Game> gameToDelete = gameRepository.findById(id);
        if (gameToDelete.isPresent()) {
            gameRepository.deleteById(id);
            LOGGER.info("Game deleted.");
        } else {
            LOGGER.info("No game found to be deleted.");
        }
    }

}
