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
    String description;
    Double price;
    @ManyToOne
    Taxonomy taxonomy;

}

