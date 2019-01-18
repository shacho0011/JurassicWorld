package com.mohit.jmc.service;

import com.mohit.jmc.dto.AnimalDto;
import com.mohit.jmc.dto.AnimalOverviewDto;
import com.mohit.jmc.model.Animal;

public interface DtoUtilService {

    AnimalOverviewDto convertToAnimalOverviewDto(Animal animal);
    AnimalDto convertToAnimalDto(Animal animal);

}
