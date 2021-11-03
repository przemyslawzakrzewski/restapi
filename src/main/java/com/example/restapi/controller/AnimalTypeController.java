package com.example.restapi.controller;

import com.example.restapi.model.AnimalType;
import com.example.restapi.service.AnimalTypeService;
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
public class AnimalTypeController {

    private final AnimalTypeService animalTypeService;

    @GetMapping("/animalTypes")
    public List<AnimalType> getAnimalTypes(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return animalTypeService.getAnimalTypes(pageNumber, sortDirection);
    }

//    @GetMapping("/animals/animalTypes")
//    public List<Animal> getAnimalsWithAnimalTypes(@RequestParam(required = false) Integer page, Sort.Direction sort) {
//        int pageNumber = page != null && page >= 0 ? page : 0;
//        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
//        return animalService.getAnimals(pageNumber, sortDirection);
//    }

    @GetMapping("/animalTypes/{id}")
    public AnimalType getSingleAnimalType(@PathVariable long id) {
        return animalTypeService.getSingleAnimalType(id);
    }

    @PostMapping("/animalTypes")
    public AnimalType addAnimalType(@RequestBody AnimalType animalType) {
        return animalTypeService.addAnimalType(animalType);
    }

    @PutMapping("/animalTypes")
    public AnimalType editAnimal(@RequestBody AnimalType animalType) {
        return animalTypeService.editAnimalType(animalType);
    }

    @DeleteMapping("/animalTypes/{id}")
    public void deleteAnimalType(@PathVariable long id) {
        animalTypeService.deleteAnimalType(id);
    }

}
