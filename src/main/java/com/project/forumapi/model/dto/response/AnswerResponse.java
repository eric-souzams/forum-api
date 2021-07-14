package com.project.forumapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AnswerResponse {

    private Long id;

    private String message;

    private OffsetDateTime createdAt;

    private Long topicId;

    private AuthorResponse author;

    private Long likeCount;

}
