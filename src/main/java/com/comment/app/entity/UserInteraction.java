package com.comment.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="user_interaction")
public class UserInteraction {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private long userId;

    private List<Long> commentFromId;

    private List<Long> commentToId;
}
