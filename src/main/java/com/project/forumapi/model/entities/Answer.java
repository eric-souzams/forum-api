package com.project.forumapi.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

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
    private Author author;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AnswerLike> likes;

    public void addLike(AnswerLike like) {
        this.getLikes().add(like);
    }

}
