package com.mohit.jmc.service;

import com.mohit.jmc.dto.AnimalDto;
import com.mohit.jmc.dto.AnimalOverviewDto;
import com.mohit.jmc.model.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoUtilServiceImpl implements DtoUtilService{

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AnimalOverviewDto convertToAnimalOverviewDto(Animal animal) {
        AnimalOverviewDto animalOverviewDto = null;

        try {
            animalOverviewDto = modelMapper.map(animal, AnimalOverviewDto.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return animalOverviewDto;
    }

    @Override
    public AnimalDto convertToAnimalDto(Animal animal) {
        AnimalDto animalDto = null;

        try {
            animalDto = modelMapper.map(animal, AnimalDto.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return animalDto;
    }
}
