package com.mohit.jmc.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true, nullable = false)
    String name;

}
