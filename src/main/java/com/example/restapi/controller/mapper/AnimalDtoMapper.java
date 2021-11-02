package com.example.restapi.controller.mapper;

import com.example.restapi.controller.dto.AnimalDto;
import com.example.restapi.model.Animal;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalDtoMapper {

    private AnimalDtoMapper() {
    }

    public static List<AnimalDto> mapToAnimalDtos(List<Animal> owners) {
        return owners.stream()
                .map(animal -> mapToAnimalDto(animal))
                .collect(Collectors.toList());
    }

    private static AnimalDto mapToAnimalDto(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .ownerId(animal.getOwnerId())
                .animalTypeId(animal.getAnimalTypeId())
                .name(animal.getName())
                .age(animal.getAge())
                .weight(animal.getWeight())
                .food(animal.getFood())
                .entertainment(animal.getEntertainment())
                .health(animal.getHealth())
                .build();
    }

}
