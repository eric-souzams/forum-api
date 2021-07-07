package com.project.forumapi.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forumapi.model.enums.TopicStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class TopicResponse {

    private Long id;

    private String title;

    private String description;

    private TopicStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime lastUpdateAt;

    private OffsetDateTime endedAt;

    private MatterResponse matter;

    private PersonResponse author;

}
