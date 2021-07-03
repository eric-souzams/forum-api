package com.project.forumapi.assembler;

import com.project.forumapi.controller.request.TopicRequest;
import com.project.forumapi.controller.response.TopicResponse;
import com.project.forumapi.model.entities.Topic;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class TopicAssembler {

    private final MatterAssembler matterAssembler;
    private final PersonAssembler personAssembler;

    public TopicResponse toResponse(Topic topic) {
        return TopicResponse
                .builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .status(topic.getStatus())
                .createdAt(topic.getCreatedAt())
                .endedAt(topic.getEndedAt())
                .matter(matterAssembler.toResponse(topic.getMatter()))
                .author(personAssembler.entityToResponse(topic.getAuthor()))
                .answers(topic.getAnswers())
                .build();
    }

    public Topic toEntity(TopicRequest topicRequest) {
        return Topic
                .builder()
                .title(topicRequest.getTitle())
                .description(topicRequest.getDescription())
                .build();
    }

    public List<TopicResponse> toResponseCollection(List<Topic> topicList) {
        return topicList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
