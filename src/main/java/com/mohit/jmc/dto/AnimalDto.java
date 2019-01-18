package com.mohit.jmc.dto;

import lombok.Data;

@Data
public class AnimalDto extends AnimalOverviewDto{

    Long id;
    String description;
    String gender;

}
