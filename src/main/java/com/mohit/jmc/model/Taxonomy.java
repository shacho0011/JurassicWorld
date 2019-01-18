package com.mohit.jmc.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Taxonomy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String kingdom;
    String phylum;
    String clazz;
    String orderr;
    String family;
    String genus;
    String species;

}
