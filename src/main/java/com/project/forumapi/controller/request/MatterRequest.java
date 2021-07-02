package com.project.forumapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MatterRequest {

    @NotBlank
    @Size(min = 2, max = 60)
    private String name;

}
