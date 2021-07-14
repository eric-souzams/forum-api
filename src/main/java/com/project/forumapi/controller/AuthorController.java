package com.project.forumapi.controller;

import com.project.forumapi.model.dto.request.AuthorRequest;
import com.project.forumapi.model.dto.response.AuthorResponse;
import com.project.forumapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private final PersonService personService;

    @GetMapping(value = "/{authorId}")
    public ResponseEntity<AuthorResponse> getOne(@PathVariable(value = "authorId") Long authorId) {
        AuthorResponse result = personService.getOne(authorId);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorResponse result = personService.save(authorRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
