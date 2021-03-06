package com.project.forumapi.service;

import com.project.forumapi.assembler.TopicAssembler;
import com.project.forumapi.model.dto.request.TopicRequest;
import com.project.forumapi.model.dto.response.TopicResponse;
import com.project.forumapi.exception.MatterNotFoundException;
import com.project.forumapi.exception.AuthorNotFoundException;
import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.exception.WithoutPermissionException;
import com.project.forumapi.model.entities.Matter;
import com.project.forumapi.model.entities.Author;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.model.enums.TopicStatus;
import com.project.forumapi.repository.MatterRepository;
import com.project.forumapi.repository.PersonRepository;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicAssembler topicAssembler;
    private final PersonRepository personRepository;
    private final MatterRepository matterRepository;

    @Transactional(readOnly = true)
    public Page<TopicResponse> findAll(Pageable pageable) {
        Page<Topic> result = topicRepository.findAll(pageable);

        return result.map(topicAssembler::toResponse);
    }

    @Transactional
    public TopicResponse create(TopicRequest topicRequest) {
        Matter matter = verifyIfExistMatter(topicRequest.getMatterId());
        Author author = verifyIfExistAuthor(topicRequest.getAuthorId());

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
        topic.setLastUpdateAt(OffsetDateTime.now());

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

    private Author verifyIfExistAuthor(Long authorId) {
        return personRepository
                .findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author not has found."));
    }

    private Topic verifyIfExistTopic(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not has found."));
    }

}
