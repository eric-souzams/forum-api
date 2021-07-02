package com.project.forumapi.controller;

import com.project.forumapi.controller.response.MatterResponse;
import com.project.forumapi.service.MatterService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
