package com.project.forumapi.assembler;

import com.project.forumapi.model.dto.request.MatterRequest;
import com.project.forumapi.model.dto.response.MatterResponse;
import com.project.forumapi.model.entities.Matter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatterAssembler {

    public MatterResponse toResponse(Matter matter) {
        MatterResponse matterResponse = new MatterResponse();
        matterResponse.setId(matter.getId());
        matterResponse.setName(matter.getName());

        return matterResponse;
    }

    public Matter toEntity(MatterRequest matterRequest) {
        Matter matter = new Matter();
        matter.setName(matterRequest.getName());

        return matter;
    }

}
