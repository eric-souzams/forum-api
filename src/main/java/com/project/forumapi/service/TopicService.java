package com.project.forumapi.service;

import com.project.forumapi.assembler.TopicAssembler;
import com.project.forumapi.controller.request.TopicRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.controller.response.TopicResponse;
import com.project.forumapi.exception.MatterNotFoundException;
import com.project.forumapi.exception.PersonNotFoundException;
import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.exception.WithoutPermissionException;
import com.project.forumapi.model.entities.Matter;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.model.enums.TopicStatus;
import com.project.forumapi.repository.MatterRepository;
import com.project.forumapi.repository.PersonRepository;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Transactional(readOnly = true)
    public List<TopicResponse> findAll() {
        return topicAssembler.toResponseCollection(topicRepository.findAll());
    }

    @Transactional
    public TopicResponse create(TopicRequest topicRequest) {
        Matter matter = verifyIfExistMatter(topicRequest.getMatterId());
        Person author = verifyIfExistAuthor(topicRequest.getAuthorId());

        Topic topic = topicAssembler.toEntity(topicRequest);
        topic.setAuthor(author);
        topic.setMatter(matter);
        topic.setStatus(TopicStatus.NOT_ANSWERED);
        topic.setCreatedAt(OffsetDateTime.now());

        Topic result = topicRepository.save(topic);

        return topicAssembler.toResponse(result);
    }

    @Transactional
    public TopicResponse update(TopicRequest topicRequest, Long topicId) {
        Topic topic = verifyIfExistTopic(topicId);
        verifyIfExistMatter(topicRequest.getMatterId());
        verifyIfExistAuthor(topicRequest.getAuthorId());

        verifyOwnerOfTopic(topic.getAuthor().getId(), topicRequest.getAuthorId());

        topic.setTitle(topicRequest.getTitle());
        topic.setDescription(topicRequest.getDescription());

        Topic result = topicRepository.save(topic);

        return topicAssembler.toResponse(result);
    }

    private void verifyOwnerOfTopic(Long topicAuthor, Long requestAuthor) {
        if (!topicAuthor.equals(requestAuthor)) {
            throw new WithoutPermissionException("You don't have permission.");
        }
    }

    private Matter verifyIfExistMatter(Long matterId) {
        return matterRepository
                .findById(matterId)
                .orElseThrow(() -> new MatterNotFoundException("Matter not has found."));
    }

    private Person verifyIfExistAuthor(Long authorId) {
        return personRepository
                .findById(authorId)
                .orElseThrow(() -> new PersonNotFoundException("Author not has found."));
    }

    private Topic verifyIfExistTopic(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not has found."));
    }

}
