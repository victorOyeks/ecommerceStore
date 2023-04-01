package com.example.weeseventask.model;


import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table (name = "human")
public class person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = false, length = 45, name = "email")
    private String email;

    @Column(nullable = false, unique = false, length = 45, name = "age")
    private String age;

    @Column(nullable = false, unique = false, length = 45, name = "height")
    private String height;
}
