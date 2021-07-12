package com.project.forumapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AnswerLikeRequest {

    @NotNull
    private Long authorId;

}
