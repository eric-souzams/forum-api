package com.project.forumapi.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class AuthorRequest {

    @NotBlank
    @Size(min = 5, max = 100)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
