package com.project.forumapi.controller;

import com.project.forumapi.assembler.PersonAssembler;
import com.project.forumapi.controller.request.PersonRequest;
import com.project.forumapi.controller.response.PersonResponse;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonAssembler personAssembler;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid PersonRequest personRequest) {
        Person person = personAssembler.requestToEntity(personRequest);

        person.setPassword(passwordEncoder.encode(person.getPassword()));

        person = personRepository.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(personAssembler.entityToResponse(person));
    }

}
