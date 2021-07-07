package com.project.forumapi.controller;

import com.project.forumapi.controller.request.PersonRequest;
import com.project.forumapi.controller.response.PersonResponse;
import com.project.forumapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/{personId}")
    public ResponseEntity<PersonResponse> getOne(@PathVariable(value = "personId") Long personId) {
        PersonResponse result = personService.getOne(personId);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody @Valid PersonRequest personRequest) {
        PersonResponse result = personService.save(personRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
