package com.mohit.jmc.service;

import com.mohit.jmc.dto.AnimalDto;
import com.mohit.jmc.model.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAllAnimal();
    List<Animal> getAnimalByName(String name);
    Animal getAnimalById(Long id);
    Animal createOrUpdateAnimal(Animal animal, AnimalDto animalDto);
    Boolean removeAnimalById(Long id);

}
