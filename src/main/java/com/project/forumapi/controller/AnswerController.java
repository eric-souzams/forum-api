package com.project.forumapi.controller;

import com.project.forumapi.model.dto.request.AnswerLikeRequest;
import com.project.forumapi.model.dto.request.AnswerRequest;
import com.project.forumapi.model.dto.response.AnswerResponse;
import com.project.forumapi.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/topics/{topicId}/answers")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerResponse> addAnswer(@PathVariable("topicId") Long topicId,
                                                    @RequestBody @Valid AnswerRequest answerRequest) {

        AnswerResponse result = answerService.addAnswer(topicId, answerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> findAll(@PathVariable("topicId") Long topicId) {
        List<AnswerResponse> result = answerService.findAll(topicId);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/{answerId}/like")
    public ResponseEntity<Void> addLike(@PathVariable("topicId") Long topicId,
                                        @PathVariable("answerId") Long answerId,
                                        @RequestBody @Valid AnswerLikeRequest answerLikeRequest) {

        answerService.addLike(topicId, answerId, answerLikeRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
