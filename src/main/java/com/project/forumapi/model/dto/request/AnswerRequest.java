package com.project.forumapi.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AnswerRequest {

    @NotNull
    private Long authorId;

    @NotBlank
    @Size(min = 20)
    private String message;
}
