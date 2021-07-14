package com.project.forumapi.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AnswerLikeRequest {

    @NotNull
    private Long authorId;

}
