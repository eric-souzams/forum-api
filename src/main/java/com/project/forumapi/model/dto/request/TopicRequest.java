package com.project.forumapi.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TopicRequest {

    @NotNull
    private Long matterId;

    @NotNull
    private Long authorId;

    @NotBlank
    @Size(min = 10, max = 100)
    private String title;

    @NotBlank
    @Size(min = 10, max = 200)
    private String description;

}
