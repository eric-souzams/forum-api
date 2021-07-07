package com.project.forumapi.assembler;

import com.project.forumapi.controller.request.PersonRequest;
import com.project.forumapi.controller.response.PersonResponse;
import com.project.forumapi.model.entities.Person;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonAssembler {

    public Person toEntity(PersonRequest personRequest) {
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setEmail(personRequest.getEmail());
        person.setPassword(personRequest.getPassword());

        return person;
    }

    public PersonResponse toResponse(Person person) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(person.getId());
        personResponse.setName(person.getName());
        personResponse.setEmail(person.getEmail());

        return personResponse;
    }

}
