package com.project.forumapi.controller.response;

import com.project.forumapi.model.enums.TopicStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class TopicResponse {

    private Long id;

    private String title;

    private String description;

    private TopicStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime endedAt;

    private MatterResponse matter;

    private PersonResponse author;

//    private List<AnswerResponse> answers;

}
