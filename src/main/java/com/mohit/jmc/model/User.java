package com.mohit.jmc.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstName;
    String lastName;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @ManyToOne
    Role role;

}
