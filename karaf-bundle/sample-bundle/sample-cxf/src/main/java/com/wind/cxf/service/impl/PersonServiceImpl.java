package com.wind.cxf.service.impl;

import com.wind.cxf.entity.Person;
import com.wind.cxf.service.PersonService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wind
 */
public class PersonServiceImpl implements PersonService {

    Map<String, Person> personMap;

    public PersonServiceImpl() {
        personMap = new HashMap<>();
        Person person = createExamplePerson();
        personMap.put("1", person);
    }

    private Person createExamplePerson() {
        Person person = new Person();
        person.setId("1");
        person.setName("Chris");
        return person;
    }

    @Override
    public Person[] getAll() {
        return personMap.values().toArray(new Person[]{});
    }

    @Override
    public Person getPerson(String id) {
        return personMap.get(id);
    }

    @Override
    public void updatePerson(String id, Person person) {
        person.setId(id);
        System.out.println("Update request received for " + person.getId() + " name:" + person.getName());
        personMap.put(id, person);
    }

    @Override
    public void addPerson(Person person) {
        System.out.println("Add request received for " + person.getId() + " name:" + person.getName());
        personMap.put(person.getId(), person);
    }

}