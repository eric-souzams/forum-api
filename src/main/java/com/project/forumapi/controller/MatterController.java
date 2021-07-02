package com.project.forumapi.controller;

import com.project.forumapi.controller.request.MatterRequest;
import com.project.forumapi.controller.response.MatterResponse;
import com.project.forumapi.service.MatterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/matters")
public class MatterController {

    private final MatterService matterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MatterResponse>> findAll() {
        List<MatterResponse> result = matterService.findAll();

        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatterResponse> create(@RequestBody @Valid MatterRequest matterRequest) {
        MatterResponse result = matterService.create(matterRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
