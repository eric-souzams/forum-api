package com.project.forumapi.assembler;

import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.model.entities.Answer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class AnswerAssembler {

    private final PersonAssembler personAssembler;

    public Answer toEntity(AnswerRequest answerRequest) {
        Answer answer = new Answer();
        answer.setMessage(answerRequest.getMessage());

        return answer;
    }

    public AnswerResponse toResponse(Answer answer) {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setId(answer.getId());
        answerResponse.setMessage(answer.getMessage());
        answerResponse.setCreatedAt(answer.getCreatedAt());
        answerResponse.setAuthor(personAssembler.toResponse(answer.getAuthor()));
        answerResponse.setTopicId(answer.getTopic().getId());

        return answerResponse;
    }

    public List<AnswerResponse> toResponseCollection(List<Answer> answers) {
        return answers.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
