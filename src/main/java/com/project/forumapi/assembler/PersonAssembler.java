package com.project.forumapi.assembler;

import com.project.forumapi.controller.request.PersonRequest;
import com.project.forumapi.controller.response.PersonResponse;
import com.project.forumapi.model.entitys.Person;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonAssembler {

    public Person requestToEntity(PersonRequest personRequest) {
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setEmail(personRequest.getEmail());
        person.setPassword(personRequest.getPassword());

        return person;
    }

    public PersonResponse entityToResponse(Person person) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(person.getId());
        personResponse.setName(person.getName());
        personResponse.setEmail(person.getEmail());
        personResponse.setPassword(person.getPassword());

        return personResponse;
    }

}
