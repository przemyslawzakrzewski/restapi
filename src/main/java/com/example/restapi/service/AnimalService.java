package com.example.restapi.service;

import com.example.restapi.model.Animal;
import com.example.restapi.repository.AnimalRepository;
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
public class AnimalService {

    private static final int PAGE_SIZE = 20;
    private final AnimalRepository animalRepository;
    private static final Logger LOGGER = LogManager.getLogger(AnimalService.class);

    @Cacheable(value = "animals", key = "#p0")
    public List<Animal> getAnimals(int page, Sort.Direction sort) {
        return animalRepository.findAllAnimals(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Cacheable(value = "singleAnimal", key = "#id")
    public Animal getSingleAnimal(long id) {
        Optional<Animal> singleAnimal = animalRepository.findById(id);
        if (singleAnimal.isPresent()) {
            LOGGER.info("Animal fetched.");
            return singleAnimal.get();
        } else {
            LOGGER.info("No single animal found.");
            return null;
        }
    }

    public Animal addAnimal(Animal animal) {
        LOGGER.info("Animal added.");
        return animalRepository.save(animal);
    }

    @Transactional
    @CachePut(value = "editAnimal")
    public Animal editAnimal(Animal animal) {
        Optional<Animal> animalToEdit = animalRepository.findById(animal.getId());
        if (animalToEdit.isPresent()) {
            LOGGER.info("Animal edited.");
            Animal animalEdited = animalToEdit.get();
            animalEdited.setName(animal.getName());
            animalEdited.setEntertainment(animal.getEntertainment());
            animalEdited.setHealth(animal.getHealth());
            animalEdited.setFood(animal.getFood());
            animalEdited.setAge(animal.getAge());
            animalEdited.setWeight(animal.getWeight());
            return animalRepository.save(animalEdited);
        } else {
            LOGGER.info("No animal found to be edited.");
            return null;
        }
    }

    @Transactional
    @CacheEvict(value = "deleteAnimal")
    public void deleteAnimal(long id) {
        Optional<Animal> animalToDelete = animalRepository.findById(id);
        if (animalToDelete.isPresent()) {
            animalRepository.deleteById(id);
            LOGGER.info("Animal deleted.");
        } else {
            LOGGER.info("No animal found to be deleted.");
        }
    }

}
