package com.mohit.jmc.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @ManyToOne
    Gender gender;
    @Lob
    String description;
    Double price;

}

