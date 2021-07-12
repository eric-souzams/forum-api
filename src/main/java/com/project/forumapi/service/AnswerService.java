package com.project.forumapi.service;

import com.project.forumapi.assembler.AnswerAssembler;
import com.project.forumapi.controller.request.AnswerLikeRequest;
import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.exception.AuthorNotFoundException;
import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.exception.WithoutPermissionException;
import com.project.forumapi.model.entities.Answer;
import com.project.forumapi.model.entities.AnswerLike;
import com.project.forumapi.model.entities.Author;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.repository.PersonRepository;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AnswerService {

    private final TopicRepository topicRepository;
    private final PersonRepository personRepository;
    private final AnswerAssembler answerAssembler;

    @Transactional
    public AnswerResponse register(Long topicId, AnswerRequest answerRequest) {
        Topic topic = verifyIfTopicExist(topicId);
        Author author = verifyIfAuthorExist(answerRequest.getAuthorId());

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

    @Transactional
    public void addLike(Long topicId, Long answerId, AnswerLikeRequest answerLikeRequest) {
        Long authorId = answerLikeRequest.getAuthorId();

        Author author = verifyIfAuthorExist(authorId);
        Topic topic = verifyIfTopicExist(topicId);
        Answer answer = verifyIfAnswerExist(topic, answerId);

        Optional<AnswerLike> result = answer.getLikes().stream()
                .filter(answerLike -> answerLike.getAuthor().getId().equals(authorId))
                .findFirst();

        if (result.isPresent()) {
            return;
        }

        AnswerLike like = new AnswerLike();
        like.setAnswer(answer);
        like.setAuthor(author);

        answer.addLike(like);
    }

    private Answer verifyIfAnswerExist(Topic topic, Long answerId) {
        return topic.getAnswers().get(answerId.intValue() - 1);
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

    private Author verifyIfAuthorExist(Long authorId) {
        return personRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Person not has found."));
    }
}
