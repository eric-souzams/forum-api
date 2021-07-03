package com.project.forumapi.model.entities;

import com.project.forumapi.model.enums.TopicStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Builder
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime endedAt;

    @ManyToOne
    private Matter matter;

    @ManyToOne
    private Person author;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers = new ArrayList<>();

}
