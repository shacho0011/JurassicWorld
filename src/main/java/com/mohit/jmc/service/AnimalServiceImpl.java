package com.mohit.jmc.service;

import com.mohit.jmc.model.Animal;
import com.mohit.jmc.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnimalServiceImpl implements AnimalService{

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public List<Animal> getAllAnimal() {
        List<Animal> animalList = null;

        try {
            animalList = animalRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        return animalList;
    }

    @Override
    public List<Animal> getAnimalByName(String name) {
        List<Animal> animalList = null;

        try {
            animalList = animalRepository.findByNameContaining(name);
        }catch (Exception e){
            e.printStackTrace();
        }

        return animalList;
    }

    @Override
    public Animal getAnimalById(Long id) {
        Animal animal = null;

        try {
            animal = animalRepository.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return animal;
    }
}
