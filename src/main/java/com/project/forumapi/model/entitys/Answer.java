package com.project.forumapi.model.entitys;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 20)
    private String message;

    private OffsetDateTime createdAt;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Person author;

}
