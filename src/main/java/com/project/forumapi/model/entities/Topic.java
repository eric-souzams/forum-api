package com.project.forumapi.model.entities;

import com.project.forumapi.model.enums.TopicStatus;

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
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime lastUpdateAt;

    private OffsetDateTime endedAt;

    @ManyToOne
    private Matter matter;

    @ManyToOne
    private Person author;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Answer addAnswer(Answer answer) {
        answer.setCreatedAt(OffsetDateTime.now());
        answer.setTopic(this);

        this.getAnswers().add(answer);

        return answer;
    }

}
