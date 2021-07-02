package com.project.forumapi.repository;

import com.project.forumapi.model.entities.Matter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatterRepository extends JpaRepository<Matter, Long> {
}
