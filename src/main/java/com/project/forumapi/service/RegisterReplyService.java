package com.project.forumapi.service;

import com.project.forumapi.assembler.AnswerAssembler;
import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.exception.PersonNotFoundException;
import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.exception.WithoutPermissionException;
import com.project.forumapi.model.entities.Answer;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.repository.PersonRepository;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class RegisterReplyService {

    private final TopicRepository topicRepository;
    private final PersonRepository personRepository;
    private final AnswerAssembler answerAssembler;

    @Transactional
    public AnswerResponse register(Long topicId, AnswerRequest answerRequest) {
        Topic topic = verifyIfTopicExist(topicId);
        Person author = verifyIfAuthorExist(answerRequest.getAuthorId());

        verifyIfAlreadyCanReply(topic.getEndedAt());

        Answer answer = answerAssembler.toEntity(answerRequest);
        answer.setAuthor(author);

        Answer result = topic.addAnswer(answer);

        return answerAssembler.toResponse(result);
    }

    public List<AnswerResponse> getAll(Long topicId) {
        Topic topic = verifyIfTopicExist(topicId);

        return answerAssembler.toResponseCollection(topic.getAnswers());
    }

    private void verifyIfAlreadyCanReply(OffsetDateTime endedAt) {
        if (endedAt != null) {
            throw new WithoutPermissionException("You has not permission to reply.");
        }
    }

    private Topic verifyIfTopicExist(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not has found."));
    }

    private Person verifyIfAuthorExist(Long authorId) {
        return personRepository.findById(authorId)
                .orElseThrow(() -> new PersonNotFoundException("Person not has found."));
    }
}
