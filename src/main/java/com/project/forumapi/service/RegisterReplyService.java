package com.project.forumapi.service;

import com.project.forumapi.assembler.AnswerAssembler;
import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.exception.PersonNotFoundException;
import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.model.entities.Answer;
import com.project.forumapi.model.entities.Person;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.repository.PersonRepository;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RegisterReplyService {

    private final TopicRepository topicRepository;
    private final PersonRepository personRepository;
    private final AnswerAssembler answerAssembler;

    @Transactional
    public AnswerResponse register(Long topicId, AnswerRequest answerRequest) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not has found."));

        Person author = personRepository.findById(answerRequest.getAuthorId())
                .orElseThrow(() -> new PersonNotFoundException("Person not has found."));

        Answer answer = answerAssembler.toEntity(answerRequest);
        answer.setAuthor(author);

        Answer result = topic.addAnswer(answer);

        return answerAssembler.toResponse(result);
    }

    public List<AnswerResponse> getAll(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not has found."));

        return answerAssembler.toResponseCollection(topic.getAnswers());
    }

}
