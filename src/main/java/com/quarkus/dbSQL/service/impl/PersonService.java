package com.quarkus.dbSQL.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import com.quarkus.dbSQL.model.Person;
import com.quarkus.dbSQL.repository.PersonRepository;
import com.quarkus.dbSQL.service.IPersonService;
import io.quarkus.panache.common.Parameters;

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
        String dniValidate = "";
        try {
            dniValidate = repository.findById(person.getId()).getDNI();
        }catch (Exception e){
            dniValidate = "";
        }
        try{
        if (dniValidate.equals("") || dniValidate.equals(null)) {
            if (person.getId() == 0 || person.getName().equals("")
                    || person.getName().equals(null)  || person.getLastName().equals("")
                    || person.getLastName().equals(null)  || person.getAge() == 0) {
                SaveValidate = "No se pudo guardar, revise los datos de entrada";
            } else {
                repository.persist(person);
                SaveValidate = "Guardado correctamente";
            }
        } else {
            SaveValidate = "El DNI ingresado ya está registrado";
        }}catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            SaveValidate = "No se pudo guardar, revise los datos de entrada";
        }
        return SaveValidate;
    }

    @Override
    public String updatePerson(Person person) {
        String UpdateResponse = "";
        if (repository.findById(person.getId()).getDNI() != null) {
            repository.update("DNI ='" +person.getDNI()+"',"+
                    "Name = '"+person.getName()+"'," +
                    "LastName = '"+person.getLastName()+"'," +
                    "Age = "+person.getAge()+ ","+
                    "country = '"+person.getCountry()+"'" +
                    " where id ="+person.getId());
            UpdateResponse = "Actualizado correctamente";
        } else {
            UpdateResponse = "No se pudo actualizar";
        }
        return UpdateResponse;
    }
}
