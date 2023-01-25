package com.quarkus.dbSQL.repository;

import javax.enterprise.context.RequestScoped;

import com.quarkus.dbSQL.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class PersonRepository implements PanacheRepository<Person> {


}
