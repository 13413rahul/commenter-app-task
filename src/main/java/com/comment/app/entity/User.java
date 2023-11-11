package com.comment.app.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

}
