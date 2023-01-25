package com.quarkus.dbSQL.service;

import java.util.List;

import com.quarkus.dbSQL.model.Person;

public interface IPersonService {

    List<Person> findAll();
    List<Person> findUpToAge(int age);
    Person findById(Long id);
    String savePerson(Person person);
    String updatePerson(Person person);


}
