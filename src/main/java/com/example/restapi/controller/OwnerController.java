package com.example.restapi.controller;

import com.example.restapi.controller.dto.OwnerDto;
import com.example.restapi.controller.mapper.OwnerDtoMapper;
import com.example.restapi.model.Owner;
import com.example.restapi.service.OwnerService;
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
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/owners")
    public List<OwnerDto> getOwners(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        List<OwnerDto> ownersDtoList = OwnerDtoMapper.mapToOwnerDtos(ownerService.getOwners(pageNumber, sortDirection));
        return ownersDtoList;
    }

    @GetMapping("/owners/animals")
    public List<Owner> getOwnersWithAnimals(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        List<Owner> ownersList = ownerService.getOwners(pageNumber, sortDirection);
        return ownersList;
    }

    @GetMapping("/owners/{id}")
    public Owner getSingleOwner(@PathVariable long id) {
        Owner owner = ownerService.getSingleOwner(id);
        return owner;
    }

    @PostMapping("/owners")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.addOwner(owner);
    }

    @PutMapping("/owners")
    public Owner editOwner(@RequestBody Owner owner) {
        return ownerService.editOwner(owner);
    }

    @DeleteMapping("/owners/{id}")
    public void deleteOwner(@PathVariable long id) {
        ownerService.deleteOwner(id);
    }

}
