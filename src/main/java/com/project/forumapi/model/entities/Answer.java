package com.project.forumapi.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private OffsetDateTime createdAt;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Person author;

}
