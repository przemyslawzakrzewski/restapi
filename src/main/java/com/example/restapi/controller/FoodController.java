package com.example.restapi.controller;

import com.example.restapi.model.Food;
import com.example.restapi.service.FoodService;
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
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/foods")
    public List<Food> getFoods(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return foodService.getFoods(pageNumber, sortDirection);
    }

    @GetMapping("/foods/{id}")
    public Food getSingleFood(@PathVariable long id) {
        return foodService.getSingleFood(id);
    }

    @PostMapping("/foods")
    public Food addFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }

    @PutMapping("/foods")
    public Food editFood(@RequestBody Food food) {
        return foodService.editFood(food);
    }

    @DeleteMapping("/foods/{id}")
    public void deleteFood(@PathVariable long id) {
        foodService.deleteFood(id);
    }

}
