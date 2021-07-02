package com.project.forumapi.service;

import com.project.forumapi.controller.assembler.MatterAssembler;
import com.project.forumapi.controller.response.MatterResponse;
import com.project.forumapi.repository.MatterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MatterService {

    private MatterRepository matterRepository;
    private MatterAssembler matterAssembler;

    public List<MatterResponse> findAll() {
        return matterAssembler.toCollectionResponse(matterRepository.findAll());
    }

}
