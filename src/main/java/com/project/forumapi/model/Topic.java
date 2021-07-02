package com.project.forumapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 100)
    private String title;

    @NotBlank
    @Size(min = 10, max = 200)
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
