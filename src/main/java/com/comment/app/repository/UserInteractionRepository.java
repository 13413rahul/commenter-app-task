package com.comment.app.repository;

import com.comment.app.entity.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
    UserInteraction findByUserId(Long id);
}
