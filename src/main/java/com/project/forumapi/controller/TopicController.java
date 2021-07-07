package com.project.forumapi.controller;

import com.project.forumapi.controller.request.AnswerRequest;
import com.project.forumapi.controller.request.TopicRequest;
import com.project.forumapi.controller.response.AnswerResponse;
import com.project.forumapi.controller.response.TopicResponse;
import com.project.forumapi.service.RegisterReplyService;
import com.project.forumapi.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicResponse>> findAll() {
        List<TopicResponse> result = topicService.findAll();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TopicResponse> create(@RequestBody @Valid TopicRequest topicRequest) {
        TopicResponse result = topicService.create(topicRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping(value = "/{topicId}")
    public ResponseEntity<TopicResponse> update(@RequestBody @Valid TopicRequest topicRequest,
                                                @PathVariable(value = "topicId") Long topicId) {

        TopicResponse result = topicService.update(topicRequest, topicId);

        return ResponseEntity.ok(result);
    }

}
