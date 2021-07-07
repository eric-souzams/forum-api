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
    private final AnswerAssembler answerAssembler;

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
                .author(personAssembler.toResponse(topic.getAuthor()))
//                .answers(answerAssembler.toResponseCollection(topic.getAnswers()))
                .build();
    }

    public Topic toEntity(TopicRequest topicRequest) {
        Topic topic = new Topic();
        topic.setTitle(topicRequest.getTitle());
        topic.setDescription(topicRequest.getDescription());

        return topic;
    }

    public List<TopicResponse> toResponseCollection(List<Topic> topicList) {
        return topicList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
