package com.project.forumapi.service;

import com.project.forumapi.assembler.MatterAssembler;
import com.project.forumapi.controller.request.MatterRequest;
import com.project.forumapi.controller.response.MatterResponse;
import com.project.forumapi.model.entities.Matter;
import com.project.forumapi.repository.MatterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class MatterService {

    private final MatterRepository matterRepository;
    private final MatterAssembler matterAssembler;

    public List<MatterResponse> findAll() {
        return matterAssembler.toCollectionResponse(matterRepository.findAll());
    }

    @Transactional
    public MatterResponse create(MatterRequest matterRequest) {
        Matter matter = matterAssembler.toEntity(matterRequest);

        matter = matterRepository.save(matter);

        return matterAssembler.toResponse(matter);
    }

}
