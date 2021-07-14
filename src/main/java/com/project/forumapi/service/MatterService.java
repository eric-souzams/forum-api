package com.project.forumapi.service;

import com.project.forumapi.assembler.MatterAssembler;
import com.project.forumapi.model.dto.request.MatterRequest;
import com.project.forumapi.model.dto.response.MatterResponse;
import com.project.forumapi.model.entities.Matter;
import com.project.forumapi.repository.MatterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class MatterService {

    private final MatterRepository matterRepository;
    private final MatterAssembler matterAssembler;

    public Page<MatterResponse> findAll(Pageable pageable) {
        Page<Matter> result = matterRepository.findAll(pageable);

        return result.map(matterAssembler::toResponse);
    }

    @Transactional
    public MatterResponse create(MatterRequest matterRequest) {
        Matter matter = matterAssembler.toEntity(matterRequest);

        matter = matterRepository.save(matter);

        return matterAssembler.toResponse(matter);
    }

}
