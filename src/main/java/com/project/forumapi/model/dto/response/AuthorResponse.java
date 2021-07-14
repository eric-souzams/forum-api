package com.project.forumapi.model.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthorResponse {

    private Long id;

    private String name;

    private String email;

}
