package com.project.forumapi.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PersonResponse {

    private Long id;

    private String name;

    private String email;

}
