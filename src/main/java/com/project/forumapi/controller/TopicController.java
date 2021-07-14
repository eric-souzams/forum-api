package com.project.forumapi.controller;

import com.project.forumapi.model.dto.request.TopicRequest;
import com.project.forumapi.model.dto.response.TopicResponse;
import com.project.forumapi.service.ChangeTopicStatusService;
import com.project.forumapi.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    private final TopicService topicService;
    private final ChangeTopicStatusService changeTopicStatusService;

    @GetMapping
    public ResponseEntity<Page<TopicResponse>> findAll(Pageable pageable) {
        Page<TopicResponse> result = topicService.findAll(pageable);

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

    @PatchMapping(value = "/{topicId}/close")
    public ResponseEntity<Void> close(@PathVariable(value = "topicId") Long topicId) {
        changeTopicStatusService.close(topicId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{topicId}/solve")
    public ResponseEntity<Void> solve(@PathVariable(value = "topicId") Long topicId) {
        changeTopicStatusService.solve(topicId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{topicId}/not-solve")
    public ResponseEntity<Void> notSolve(@PathVariable(value = "topicId") Long topicId) {
        changeTopicStatusService.notSolve(topicId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
