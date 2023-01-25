package com.quarkus.dbSQL.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import com.quarkus.dbSQL.model.Person;
import com.quarkus.dbSQL.repository.PersonRepository;
import com.quarkus.dbSQL.service.IPersonService;

@ApplicationScoped
public class PersonService implements IPersonService {

    @Inject
    PersonRepository repository;

    @Override
    public List<Person> findAll() {
        return repository.listAll();
    }

    @Override
    public List<Person> findUpToAge(int age) {

        List<Person> allPerson = repository.listAll().stream().filter(c -> c.getAge() > age)
                .collect(Collectors.toList());

        return allPerson;
    }

    @Override
    public Person findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public String savePerson(Person person) {
        String SaveValidate = "";
        String dniValidate = repository.findById(person.getId()).getDNI();
        if (dniValidate.equals("") || dniValidate.equals(null)) {
            if (person.getId() == 0 || person.getName() == ""
                    || person.getName() == null || person.getLastName() == ""
                    || person.getLastName() == null || person.getAge() == 0) {
                SaveValidate = "No se pudo guardar, revise los datos de entrada";
            } else {
                repository.persist(person);
                SaveValidate = "Guardado correctamente";
            }
        } else {
            SaveValidate = "El DNI ingresado ya está registrado";
        }
        return SaveValidate;
    }

    @Override
    public String updatePerson(Person person) {
        String UpdateResponse = "";
        Person personBefore = findById(person.getId());
        if (repository.findById(person.getId()).getDNI() != null) {
            repository.update("");
            UpdateResponse = "Actualizado correctamente";
        } else {
            UpdateResponse = "No se pudo actualizar";
        }
        return UpdateResponse;
    }
}
