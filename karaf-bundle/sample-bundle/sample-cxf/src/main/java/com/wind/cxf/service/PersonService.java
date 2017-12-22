package com.wind.cxf.service;

import com.wind.cxf.entity.Person;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author wind
 */
@Produces(MediaType.APPLICATION_XML)
public interface PersonService {

    @GET
    @Path("/")
    Person[] getAll();

    @GET
    @Path("/{id}")
    Person getPerson(@PathParam("id") String id);

    @PUT
    @Path("/{id}")
    void updatePerson(@PathParam("id") String id, Person person);

    @POST
    @Path("/")
    void addPerson(Person person);
}
