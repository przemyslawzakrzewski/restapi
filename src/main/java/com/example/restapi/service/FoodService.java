package com.example.restapi.service;

import com.example.restapi.model.Food;
import com.example.restapi.repository.FoodRepository;
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
public class FoodService {

    private static final int PAGE_SIZE = 20;
    private final FoodRepository foodRepository;
    private static final Logger LOGGER = LogManager.getLogger(FoodService.class);

    @Cacheable(value = "foods", key = "#p0")
    public List<Food> getFoods(int page, Sort.Direction sort) {
        return foodRepository.findAllFoods(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Cacheable(value = "singleFood", key = "#id")
    public Food getSingleFood(long id) {
        Optional<Food> singleFood = foodRepository.findById(id);
        if (singleFood.isPresent()) {
            LOGGER.info("Food fetched.");
            return singleFood.get();
        } else {
            LOGGER.info("No single food found.");
            return null;
        }
    }

    public Food addFood(Food food) {
        LOGGER.info("Food added.");
        return foodRepository.save(food);
    }

    @Transactional
    @CachePut(value = "editFood")
    public Food editFood(Food food) {
        Optional<Food> foodToEdit = foodRepository.findById(food.getId());
        if (foodToEdit.isPresent()) {
            LOGGER.info("Food edited.");
            Food foodEdited = foodToEdit.get();
            foodEdited.setName(food.getName());
            foodEdited.setValue(food.getValue());
            return foodRepository.save(foodEdited);
        } else {
            LOGGER.info("No food found to be edited.");
            return null;
        }
    }

    @Transactional
    @CacheEvict(value = "deleteFood")
    public void deleteFood(long id) {
        Optional<Food> foodToDelete = foodRepository.findById(id);
        if (foodToDelete.isPresent()) {
            foodRepository.deleteById(id);
            LOGGER.info("Food deleted.");
        } else {
            LOGGER.info("No food found to be deleted.");
        }
    }

}
