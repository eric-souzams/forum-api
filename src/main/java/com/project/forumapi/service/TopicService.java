package com.project.forumapi.service;

import com.project.forumapi.assembler.TopicAssembler;
import com.project.forumapi.controller.request.TopicRequest;
import com.project.forumapi.controller.response.TopicResponse;
import com.project.forumapi.exception.MatterNotFoundException;
import com.project.forumapi.exception.PersonNotFoundException;
import com.project.forumapi.model.entities.Matter;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.model.enums.TopicStatus;
import com.project.forumapi.repository.MatterRepository;
import com.project.forumapi.repository.PersonRepository;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicAssembler topicAssembler;
    private final PersonRepository personRepository;
    private final MatterRepository matterRepository;

    public List<TopicResponse> findAll() {
        return topicAssembler.toResponseCollection(topicRepository.findAll());
    }

    @Transactional
    public TopicResponse create(TopicRequest topicRequest) {
        Matter matter = matterRepository
                .findById(topicRequest.getMatterId())
                .orElseThrow(() -> new MatterNotFoundException("Matter not has found."));

        Person author = personRepository
                .findById(topicRequest.getAuthorId())
                .orElseThrow(() -> new PersonNotFoundException("Author not has found."));

        Topic topic = topicAssembler.toEntity(topicRequest);
        topic.setAuthor(author);
        topic.setMatter(matter);
        topic.setStatus(TopicStatus.NOT_ANSWERED);
        topic.setCreatedAt(OffsetDateTime.now());

        Topic result = topicRepository.save(topic);

        return topicAssembler.toResponse(result);
    }

}
