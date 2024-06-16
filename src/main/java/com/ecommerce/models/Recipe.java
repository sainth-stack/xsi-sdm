package com.ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private User user;
    private String image;
    private String description;
    private boolean bagitarian;
    private LocalDateTime createdAt;
    private List<Long> likes = new ArrayList<>();
}
