package com.comment.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime commentDateTime;

    private Long commentedByUserId;

    @PrePersist
    public void prePersist() {
        commentDateTime = LocalDateTime.now();
    }

}
