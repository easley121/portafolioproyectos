package com.nekorp.portafolio.proyectos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {


    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, length = 200)
    private String title;

    @Setter
    @Getter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Setter
    @Getter
    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    private ProjectType projectType;

    @Setter
    @Getter
    @Column(name = "technologies", length = 500)
    private String technologies; // Java,Spring Boot,PostgreSQL

    @Setter
    @Getter
    @Column(name = "github_url")
    private String githubUrl;

    @Setter
    @Getter
    @Column(name = "demo_url")
    private String demoUrl;

    @Setter
    @Getter
    @Column(name = "image_path")
    private String imagePath;

    @Setter
    @Getter
    @Column(name = "gallery_paths", columnDefinition = "TEXT")
    private String galleryPaths; // Separadas por comas

    @Setter
    @Getter
    @Column(name = "featured")
    private boolean featured = false;

    @Setter
    @Getter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @Getter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructores
    public Project() {}

    public Project(String title, String description, String shortDescription,
                   ProjectType projectType, String technologies) {
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.projectType = projectType;
        this.technologies = technologies;
    }

}