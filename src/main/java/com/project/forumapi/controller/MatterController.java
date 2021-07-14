package com.project.forumapi.controller;

import com.project.forumapi.model.dto.request.MatterRequest;
import com.project.forumapi.model.dto.response.MatterResponse;
import com.project.forumapi.service.MatterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/matters")
public class MatterController {

    private final MatterService matterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<MatterResponse>> findAll(Pageable pageable) {
        Page<MatterResponse> result = matterService.findAll(pageable);

        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatterResponse> create(@RequestBody @Valid MatterRequest matterRequest) {
        MatterResponse result = matterService.create(matterRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
