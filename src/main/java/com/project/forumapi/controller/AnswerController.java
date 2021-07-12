package com.project.forumapi.controller;

import com.project.forumapi.controller.request.AnswerLikeRequest;
import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.response.AnswerResponse;
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

        AnswerResponse result = answerService.register(topicId, answerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers(@PathVariable("topicId") Long topicId) {
        List<AnswerResponse> result = answerService.getAll(topicId);

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
