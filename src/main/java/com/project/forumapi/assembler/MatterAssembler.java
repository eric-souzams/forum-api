package com.project.forumapi.assembler;

import com.project.forumapi.controller.response.MatterResponse;
import com.project.forumapi.model.entitys.Matter;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MatterAssembler {

    public MatterResponse toResponse(Matter matter) {
        MatterResponse matterResponse = new MatterResponse();
        matterResponse.setId(matter.getId());
        matterResponse.setName(matter.getName());

        return matterResponse;
    }

    public List<MatterResponse> toCollectionResponse(List<Matter> matters) {
        return matters.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
