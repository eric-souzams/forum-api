package com.project.forumapi.service;

import com.project.forumapi.assembler.PersonAssembler;
import com.project.forumapi.controller.request.PersonRequest;
import com.project.forumapi.controller.response.PersonResponse;
import com.project.forumapi.exception.PersonNotFoundException;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonAssembler personAssembler;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PersonResponse save(PersonRequest personRequest) {
        Person person = personAssembler.toEntity(personRequest);

        person.setPassword(passwordEncoder.encode(person.getPassword()));

        person = personRepository.save(person);

        return personAssembler.toResponse(person);
    }

    @Transactional(readOnly = true)
    public PersonResponse getOne(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not has found."));

        return personAssembler.toResponse(person);
    }

}
