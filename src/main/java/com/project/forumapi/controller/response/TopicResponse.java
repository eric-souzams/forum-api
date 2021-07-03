package com.project.forumapi.controller.response;

import com.project.forumapi.model.entities.Answer;
import com.project.forumapi.model.entities.Matter;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.model.enums.TopicStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    private List<Answer> answers = new ArrayList<>();

}
