package com.project.forumapi.controller;

import com.project.forumapi.controller.request.AuthorRequest;
import com.project.forumapi.controller.response.AuthorResponse;
import com.project.forumapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/person")
public class AuthorController {

    private final PersonService personService;

    @GetMapping(value = "/{personId}")
    public ResponseEntity<AuthorResponse> getOne(@PathVariable(value = "personId") Long personId) {
        AuthorResponse result = personService.getOne(personId);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorResponse result = personService.save(authorRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
