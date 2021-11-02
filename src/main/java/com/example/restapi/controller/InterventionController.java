package com.example.restapi.controller;

import com.example.restapi.model.Intervention;
import com.example.restapi.service.InterventionService;
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
public class InterventionController {

    private final InterventionService interventionService;

    @GetMapping("/interventions")
    public List<Intervention> getInterventions(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return interventionService.getInterventions(pageNumber, sortDirection);
    }

    @GetMapping("/interventions/{id}")
    public Intervention getSingleIntervention(@PathVariable long id) {
        return interventionService.getSingleIntervention(id);
    }

    @PostMapping("/interventions")
    public Intervention addIntervention(@RequestBody Intervention intervention) {
        return interventionService.addIntervention(intervention);
    }

    @PutMapping("/interventions")
    public Intervention editIntervention(@RequestBody Intervention intervention) {
        return interventionService.editIntervention(intervention);
    }

    @DeleteMapping("/interventions/{id}")
    public void deleteIntervention(@PathVariable long id) {
        interventionService.deleteIntervention(id);
    }

}
