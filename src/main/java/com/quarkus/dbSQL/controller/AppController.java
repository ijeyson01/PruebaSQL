package com.quarkus.dbSQL.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.quarkus.dbSQL.model.Person;
import com.quarkus.dbSQL.service.impl.PersonService;

@Path("/v1")
public class AppController {

    @Inject
    PersonService service;

    @Transactional
    @Path("AllPerson")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response AllPerson() {
        return Response.ok().entity(service.findAll()).build();
    }

    @Transactional
    @Path("Person/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPerson(@PathParam("id") Integer id) {
        return Response.ok().entity(service.findById(id)).build();
    }

    @Transactional
    @Path("PersonAge/{age}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonAge(@PathParam("age") int age) {
        return Response.ok().entity(service.findUpToAge(age)).build();
    }

    @Transactional
    @Path("SavePerson")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePerson(Person person) {
        return Response.ok(service.savePerson(person)).build();
    }

    @Transactional
    @Path("UpdatePerson")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        return Response.ok(service.updatePerson(person)).build();
    }

}