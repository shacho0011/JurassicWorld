package com.mohit.jmc.repository;

import com.mohit.jmc.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
	
	Gender findByName(String name);
	
}
