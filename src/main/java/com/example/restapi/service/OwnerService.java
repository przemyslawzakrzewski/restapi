package com.example.restapi.service;

import com.example.restapi.model.Animal;
import com.example.restapi.model.Owner;
import com.example.restapi.repository.OwnerRepository;
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
public class OwnerService {

    private static final int PAGE_SIZE = 20;
    private final OwnerRepository ownerRepository;
    private static final Logger LOGGER = LogManager.getLogger(OwnerService.class);

    @Cacheable(value = "owners", key = "#p0")
    public List<Owner> getOwners(int page, Sort.Direction sort) {
        LOGGER.info("Owners fetched.");
        return ownerRepository.findAllOwners(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Cacheable(value = "singleOwner", key = "#id")
    public Owner getSingleOwner(long id) {
        Optional<Owner> singleOwner = ownerRepository.findById(id);
        if (singleOwner.isPresent()) {
            LOGGER.info("Owner fetched.");
            return singleOwner.get();
        } else {
            LOGGER.info("No single owner found.");
            return null;
        }
    }

    public Owner addOwner(Owner owner) {
        LOGGER.info("Owner added.");
        return ownerRepository.save(owner);
    }

    @Transactional
    @CachePut(value = "editOwner")
    public Owner editOwner(Owner owner) {
        Optional<Owner> ownerToEdit = ownerRepository.findById(owner.getId());
        if (ownerToEdit.isPresent()) {
            LOGGER.info("Owner edited.");
            Owner ownerEdited = ownerToEdit.get();
            ownerEdited.setFirst_name(owner.getFirst_name());
            ownerEdited.setLast_name(owner.getLast_name());
            ownerEdited.setMobile(owner.getMobile());
            ownerEdited.setEmail(owner.getEmail());
            ownerEdited.setNotes(owner.getNotes());
            return ownerRepository.save(ownerEdited);
        } else {
            LOGGER.info("No owner found to be edited.");
            return null;
        }
    }

    @Transactional
    @CacheEvict(value = "deleteOwner")
    public void deleteOwner(long id) {
        Optional<Owner> ownerToDelete = ownerRepository.findById(id);
        if (ownerToDelete.isPresent()) {
            List<Animal> animals = ownerToDelete.get().getAnimals();
            for (Animal animal : animals) {
                animal.setOwnerId(null);
            }
            ownerRepository.deleteById(id);
            LOGGER.info("Owner deleted.");
        } else {
            LOGGER.info("No owner found to be deleted.");
        }
    }

}
