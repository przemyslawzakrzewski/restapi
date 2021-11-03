package com.example.restapi.service;

import com.example.restapi.model.AnimalType;
import com.example.restapi.repository.AnimalTypeRepository;
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
public class AnimalTypeService {

    private static final int PAGE_SIZE = 20;
    private final AnimalTypeRepository animalTypeRepository;
    private static final Logger LOGGER = LogManager.getLogger(AnimalTypeService.class);

    @Cacheable(value = "animalTypes", key = "#p0")
    public List<AnimalType> getAnimalTypes(int page, Sort.Direction sort) {
        return animalTypeRepository.findAllAnimalTypes(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Cacheable(value = "singleAnimalType", key = "#id")
    public AnimalType getSingleAnimalType(long id) {
        Optional<AnimalType> singleAnimalType = animalTypeRepository.findById(id);
        if (singleAnimalType.isPresent()) {
            LOGGER.info("Animal type fetched.");
            return singleAnimalType.get();
        } else {
            LOGGER.info("No single animal type found.");
            return null;
        }
    }

    public AnimalType addAnimalType(AnimalType animalType) {
        LOGGER.info("Animal type added.");
        return animalTypeRepository.save(animalType);
    }

    @Transactional
    @CachePut(value = "editAnimalType")
    public AnimalType editAnimalType(AnimalType animalType) {
        Optional<AnimalType> animalTypeToEdit = animalTypeRepository.findById(animalType.getId());
        if (animalTypeToEdit.isPresent()) {
            LOGGER.info("Animal type edited.");
            AnimalType animalTypeEdited = animalTypeToEdit.get();
            animalTypeEdited.setName(animalType.getName());
            animalTypeEdited.setThumbnail(animalType.getThumbnail());
            animalTypeEdited.setInterventions(animalType.getInterventions());
            animalTypeEdited.setGames(animalType.getGames());
            animalTypeEdited.setFoods(animalType.getFoods());
            return animalTypeRepository.save(animalTypeEdited);
        } else {
            LOGGER.info("No animal type found to be edited.");
            return null;
        }
    }

    @Transactional
    @CacheEvict(value = "deleteAnimalType")
    public void deleteAnimalType(long id) {
        Optional<AnimalType> animalTypeToDelete = animalTypeRepository.findById(id);
        if (animalTypeToDelete.isPresent()) {
            animalTypeRepository.deleteById(id);
            LOGGER.info("Animal type deleted.");
        } else {
            LOGGER.info("No Animal type found to be deleted.");
        }
    }

}
