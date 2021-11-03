package com.example.restapi.service;

import com.example.restapi.model.Intervention;
import com.example.restapi.repository.InterventionRepository;
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
public class InterventionService {

    private static final int PAGE_SIZE = 20;
    private final InterventionRepository interventionRepository;
    private static final Logger LOGGER = LogManager.getLogger(InterventionService.class);

    @Cacheable(value = "interventions", key = "#p0")
    public List<Intervention> getInterventions(int page, Sort.Direction sort) {
        return interventionRepository.findAllInterventions(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                ));
    }

    @Cacheable(value = "singleIntervention", key = "#id")
    public Intervention getSingleIntervention(long id) {
        Optional<Intervention> singleIntervention = interventionRepository.findById(id);
        if (singleIntervention.isPresent()) {
            LOGGER.info("Intervention fetched.");
            return singleIntervention.get();
        } else {
            LOGGER.info("No single intervention found.");
            return null;
        }
    }

    public Intervention addIntervention(Intervention intervention) {
        LOGGER.info("Intervention added.");
        return interventionRepository.save(intervention);
    }

    @Transactional
    @CachePut(value = "editIntervention")
    public Intervention editIntervention(Intervention intervention) {
        Optional<Intervention> interventionToEdit = interventionRepository.findById(intervention.getId());
        if (interventionToEdit.isPresent()) {
            LOGGER.info("Intervention edited.");
            Intervention interventionEdited = interventionToEdit.get();
            interventionEdited.setName(intervention.getName());
            interventionEdited.setValue(intervention.getValue());
            return interventionRepository.save(interventionEdited);
        } else {
            LOGGER.info("No intervention found to be edited.");
            return null;
        }
    }

    @Transactional
    @CacheEvict(value = "deleteIntervention")
    public void deleteIntervention(long id) {
        Optional<Intervention> interventionToDelete = interventionRepository.findById(id);
        if (interventionToDelete.isPresent()) {
            interventionRepository.deleteById(id);
            LOGGER.info("Intervention deleted.");
        } else {
            LOGGER.info("No intervention found to be deleted.");
        }
    }

}
