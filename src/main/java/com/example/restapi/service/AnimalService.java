package com.example.restapi.service;

import com.example.restapi.model.Animal;
import com.example.restapi.repository.AnimalRepository;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private static final int PAGE_SIZE = 20;
    private final AnimalRepository animalRepository;

    public List<Animal> getAnimals(int page, Sort.Direction sort) {
        return animalRepository.findAllAnimals(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    public Animal getSingleAnimal(long id) {
        return animalRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal editAnimal(Animal animal) {
        Animal animalEdited = animalRepository.findById(animal.getId()).orElseThrow(NoSuchElementException::new);
        animalEdited.setName(animal.getName());
        animalEdited.setAge(animal.getAge());
        animalEdited.setWeight(animal.getWeight());
        animalEdited.setFood(animal.getFood());
        animalEdited.setEntertainment(animal.getEntertainment());
        animalEdited.setHealth(animal.getHealth());
        return animalRepository.save(animalEdited);
    }

    public void deleteAnimal(long id) {
        animalRepository.deleteById(id);
    }
}
