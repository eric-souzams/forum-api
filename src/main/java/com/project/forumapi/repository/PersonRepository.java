package com.project.forumapi.repository;

import com.project.forumapi.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Author, Long> {

}
