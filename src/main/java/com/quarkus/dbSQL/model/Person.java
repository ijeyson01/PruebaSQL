package com.quarkus.dbSQL.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    private Integer Id;

    private String DNI;
    private String name;
    private String LastName;
    private int Age;
    private String country;


}
