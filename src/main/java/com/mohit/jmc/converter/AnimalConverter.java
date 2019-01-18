package com.mohit.jmc.converter;

import com.mohit.jmc.dto.AnimalDto;
import com.mohit.jmc.model.Animal;
import com.mohit.jmc.model.Gender;
import com.mohit.jmc.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AnimalConverter {

    @Autowired
    GenderRepository genderRepository;

    public Animal overwriteAnimal(AnimalDto dto, Animal animal){

        if(dto.getName() != null){
            animal.setName(dto.getName());
        }
        if(dto.getGender() != null){
            Gender gender = null;
            try {
                Long id = Long.parseLong(dto.getGender());
                gender = genderRepository.findOne(id);
            }catch (Exception e){
                e.printStackTrace();
            }
            animal.setGender(gender);
        }
        if(dto.getPrice() != null){
            animal.setPrice(dto.getPrice());
        }
        if(dto.getDescription() != null){
            animal.setDescription(dto.getDescription());
        }

        return animal;
    }
}
