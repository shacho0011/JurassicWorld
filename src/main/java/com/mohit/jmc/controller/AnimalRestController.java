package com.mohit.jmc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.jmc.dto.AnimalDto;
import com.mohit.jmc.dto.AnimalOverviewDto;
import com.mohit.jmc.model.Animal;
import com.mohit.jmc.model.User;
import com.mohit.jmc.service.AnimalService;
import com.mohit.jmc.service.DtoUtilService;
import com.mohit.jmc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
	@Autowired
	UserService userService;

	@GetMapping("/animals")
	ResponseEntity<Object> getAllAnimal(@RequestHeader("animal_id") Optional<Long> id) {
		ResponseEntity<Object> responseEntity = null;

		try {

			if (id.isPresent()) {
				Animal animal = animalService.getAnimalById(id.get());
				if (animal != null) {
					AnimalOverviewDto animalOverviewDto = dtoUtilService.convertToAnimalOverviewDto(animal);
					responseEntity = new ResponseEntity<>(animalOverviewDto, null, HttpStatus.OK);
				} else {
					responseEntity = new ResponseEntity<>("No animal available!", null, HttpStatus.OK);
				}

			} else {
				List<Animal> animalList = animalService.getAllAnimal();
				if (animalList.size() > 0) {
					List<AnimalOverviewDto> animalOverviewDtoList = new ArrayList<>();
					animalList.forEach(animal -> {
						animalOverviewDtoList.add(dtoUtilService.convertToAnimalOverviewDto(animal));
					});
					responseEntity = new ResponseEntity<>(animalOverviewDtoList, null, HttpStatus.OK);
				} else {
					responseEntity = new ResponseEntity<>("No animal available!", null, HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@GetMapping("/search/animals")
	ResponseEntity<Object> getAnimalsByName(@RequestHeader("animal_name") String name) {
		ResponseEntity<Object> responseEntity = null;

		try {

			List<Animal> animalList = animalService.getAnimalByName(name);
			if (animalList.size() > 0) {
				List<AnimalOverviewDto> animalOverviewDtoList = new ArrayList<>();
				animalList.forEach(animal -> {
					animalOverviewDtoList.add(dtoUtilService.convertToAnimalOverviewDto(animal));
				});
				responseEntity = new ResponseEntity<>(animalOverviewDtoList, null, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("No animal available!", null, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@PostMapping("/insert/animals/new")
	ResponseEntity<Object> insertAnimal(@RequestBody String requestData) {
		ResponseEntity<Object> responseEntity = null;
		AnimalDto animalDto = null;
		Animal animal = null;

		try {

			animalDto = new ObjectMapper().readValue(requestData, AnimalDto.class);
			animal = new Animal();
			animal = animalService.createOrUpdateAnimal(animal, animalDto);
			responseEntity = new ResponseEntity<>("Insert operation successful", null, HttpStatus.OK);

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@PutMapping("/update/animals")
	ResponseEntity<Object> updateAnimal(@RequestHeader("animal_id") Long id, @RequestBody String requestData) {
		ResponseEntity<Object> responseEntity = null;
		AnimalDto animalDto = null;
		Animal animal = null;

		try {

			animal = animalService.getAnimalById(id);
			animalDto = new ObjectMapper().readValue(requestData, AnimalDto.class);
			animalService.createOrUpdateAnimal(animal, animalDto);
			responseEntity = new ResponseEntity<>("Update operation successful", null, HttpStatus.OK);

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

	@DeleteMapping("/delete/animals")
	ResponseEntity<Object> deleteAnimal(Principal principal, @RequestHeader("animal_id") Long id) {
		ResponseEntity<Object> responseEntity = null;
		User user = null;

		try {

			user = userService.getUserDetails(principal);
			System.out.println(user != null ? user.getEmail() : "null");
			if (user != null && user.getRole().getName().toLowerCase().equals("admin")) {
				animalService.removeAnimalById(id);
				responseEntity = new ResponseEntity<>("Delete operation successful", null, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("Forbidden access", null, HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

}
