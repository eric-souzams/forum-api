package com.project.forumapi.service;

import com.project.forumapi.assembler.AuthorAssembler;
import com.project.forumapi.controller.request.AuthorRequest;
import com.project.forumapi.controller.response.AuthorResponse;
import com.project.forumapi.exception.AuthorNotFoundException;
import com.project.forumapi.model.entities.Author;
import com.project.forumapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AuthorAssembler authorAssembler;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthorResponse save(AuthorRequest authorRequest) {
        Author author = authorAssembler.toEntity(authorRequest);

        author.setPassword(passwordEncoder.encode(author.getPassword()));

        author = personRepository.save(author);

        return authorAssembler.toResponse(author);
    }

    @Transactional(readOnly = true)
    public AuthorResponse getOne(Long personId) {
        Author author = verifyIfAuthorExist(personId);

        return authorAssembler.toResponse(author);
    }

    private Author verifyIfAuthorExist(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new AuthorNotFoundException("Person not has found."));
    }

}
