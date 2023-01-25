package com.quarkus.dbSQL.repository;

import javax.enterprise.context.RequestScoped;

import com.quarkus.dbSQL.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class PersonRepository implements PanacheRepositoryBase<Person, Integer> {


}
