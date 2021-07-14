package com.project.forumapi.assembler;

import com.project.forumapi.model.dto.request.TopicRequest;
import com.project.forumapi.model.dto.response.TopicResponse;
import com.project.forumapi.model.entities.Topic;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class TopicAssembler {

    private final MatterAssembler matterAssembler;
    private final AuthorAssembler authorAssembler;

    public TopicResponse toResponse(Topic topic) {
        return TopicResponse
                .builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .status(topic.getStatus())
                .createdAt(topic.getCreatedAt())
                .lastUpdateAt(topic.getLastUpdateAt())
                .endedAt(topic.getEndedAt())
                .matter(matterAssembler.toResponse(topic.getMatter()))
                .author(authorAssembler.toResponse(topic.getAuthor()))
                .build();
    }

    public Topic toEntity(TopicRequest topicRequest) {
        Topic topic = new Topic();
        topic.setTitle(topicRequest.getTitle());
        topic.setDescription(topicRequest.getDescription());

        return topic;
    }

}
