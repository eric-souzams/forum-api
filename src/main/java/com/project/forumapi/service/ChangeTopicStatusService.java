package com.project.forumapi.service;

import com.project.forumapi.exception.TopicNotFoundException;
import com.project.forumapi.model.entities.Topic;
import com.project.forumapi.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ChangeTopicStatusService {

    private final TopicRepository topicRepository;

    @Transactional
    public void close(Long topicId) {
        Topic topic = verifyIfTopicExist(topicId);

        topic.closeTopic();

        topicRepository.save(topic);
    }

    @Transactional
    public void solve(Long topicId) {
        Topic topic = verifyIfTopicExist(topicId);

        topic.solveTopic();

        topicRepository.save(topic);
    }

    @Transactional
    public void notSolve(Long topicId) {
        Topic topic = verifyIfTopicExist(topicId);

        topic.notSolvedTopic();

        topicRepository.save(topic);
    }

    private Topic verifyIfTopicExist(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not has found."));
    }

}
