package com.project.forumapi.controller;

import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.service.RegisterReplyService;
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

    private final RegisterReplyService registerReplyService;

    @PostMapping
    public ResponseEntity<AnswerResponse> addAnswer(@PathVariable("topicId") Long topicId,
                                                    @RequestBody @Valid AnswerRequest answerRequest) {

        AnswerResponse result = registerReplyService.register(topicId, answerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers(@PathVariable("topicId") Long topicId) {
        List<AnswerResponse> result = registerReplyService.getAll(topicId);

        return ResponseEntity.ok(result);
    }

}
