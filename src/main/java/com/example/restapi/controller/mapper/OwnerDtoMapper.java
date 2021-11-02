package com.example.restapi.controller.mapper;

import com.example.restapi.controller.dto.OwnerDto;
import com.example.restapi.model.Owner;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerDtoMapper {

    private OwnerDtoMapper() {
    }

    public static List<OwnerDto> mapToOwnerDtos(List<Owner> owners) {
        return owners.stream()
                .map(owner -> mapToOwnerDto(owner))
                .collect(Collectors.toList());
    }

    private static OwnerDto mapToOwnerDto(Owner owner) {
        return OwnerDto.builder()
                .id(owner.getId())
                .first_name(owner.getFirst_name())
                .last_name(owner.getLast_name())
                .mobile(owner.getMobile())
                .email(owner.getEmail())
                .notes(owner.getNotes())
                .build();
    }

}
