package com.mohit.jmc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.jmc.dto.AnimalDto;
import com.mohit.jmc.dto.AnimalOverviewDto;
import com.mohit.jmc.model.Animal;
import com.mohit.jmc.service.AnimalService;
import com.mohit.jmc.service.DtoUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AnimalRestController {

    @Autowired
    AnimalService animalService;
    @Autowired
    DtoUtilService dtoUtilService;

    @GetMapping("/animals")
    ResponseEntity<Object> getAllAnimal(@RequestHeader Optional<Long> id){
        ResponseEntity<Object> responseEntity = null;

        try {

            if(id.isPresent()){
                Animal animal = animalService.getAnimalById(id.get());
                AnimalOverviewDto animalOverviewDto = dtoUtilService.convertToAnimalOverviewDto(animal);
                responseEntity = new ResponseEntity<>(animalOverviewDto, null, HttpStatus.OK);
            }else{
                List<Animal> animalList = animalService.getAllAnimal();
                if(animalList.size() > 0){
                    List<AnimalOverviewDto> animalOverviewDtoList = new ArrayList<>();
                    animalList.forEach(animal -> {
                        animalOverviewDtoList.add(dtoUtilService.convertToAnimalOverviewDto(animal));
                    });
                    responseEntity = new ResponseEntity<>(animalOverviewDtoList, null, HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity<>("No animal available!", null, HttpStatus.OK);
                }
            }

        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        return  responseEntity;
    }

    @GetMapping("/animal")
    ResponseEntity<Object> getAnimalsByName(@RequestHeader String name){
        ResponseEntity<Object> responseEntity = null;

        try {

            List<Animal> animalList = animalService.getAnimalByName(name);
            if(animalList.size() > 0){
                List<AnimalOverviewDto> animalOverviewDtoList = new ArrayList<>();
                animalList.forEach(animal -> {
                    animalOverviewDtoList.add(dtoUtilService.convertToAnimalOverviewDto(animal));
                });
                responseEntity = new ResponseEntity<>(animalOverviewDtoList, null, HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>("No animal available!", null, HttpStatus.OK);
            }

        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        return  responseEntity;
    }

    @PostMapping("/insert/animal/new")
    ResponseEntity<Object> insertAnimal(@RequestBody String requestData){
        ResponseEntity<Object> responseEntity = null;
        AnimalDto animalDto = null;

        try {

            animalDto = new ObjectMapper().readValue(requestData, AnimalDto.class);
            animalService.createOrUpdateAnimal(animalDto);
            responseEntity = new ResponseEntity<>("Success", null, HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

        return  responseEntity;
    }

}
