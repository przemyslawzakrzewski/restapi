package com.example.restapi.controller;

import com.example.restapi.controller.dto.AnimalDto;
import com.example.restapi.controller.mapper.AnimalDtoMapper;
import com.example.restapi.model.Animal;
import com.example.restapi.service.AnimalService;
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
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/animals")
    public List<AnimalDto> getAnimals(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return AnimalDtoMapper.mapToAnimalDtos(animalService.getAnimals(pageNumber, sortDirection));
    }

    @GetMapping("/animals/animalTypes")
    public List<Animal> getAnimalsWithAnimalTypes(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return animalService.getAnimals(pageNumber, sortDirection);
    }

    @GetMapping("/animals/{id}")
    public Animal getSingleAnimal(@PathVariable long id) {
        return animalService.getSingleAnimal(id);
    }

    @PostMapping("/animals")
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }

    @PutMapping("/animals")
    public Animal editAnimal(@RequestBody Animal animal) {
        return animalService.editAnimal(animal);
    }

    @DeleteMapping("/animals/{id}")
    public void deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimal(id);
    }

}
