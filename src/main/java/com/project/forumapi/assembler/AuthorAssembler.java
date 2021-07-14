package com.project.forumapi.assembler;

import com.project.forumapi.model.dto.request.AuthorRequest;
import com.project.forumapi.model.dto.response.AuthorResponse;
import com.project.forumapi.model.entities.Author;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorAssembler {

    public Author toEntity(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setEmail(authorRequest.getEmail());
        author.setPassword(authorRequest.getPassword());

        return author;
    }

    public AuthorResponse toResponse(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setEmail(author.getEmail());

        return authorResponse;
    }

}
