package com.nekorp.portafolio.proyectos.dto;

import com.nekorp.portafolio.proyectos.entity.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectDTO {

    private Long id;

    @NotBlank(message = "El título es requerido")
    @Size(max = 200, message = "El título no puede exceder 200 caracteres")
    private String title;

    @NotBlank(message = "La descripción es requerida")
    private String description;

    @Size(max = 500, message = "La descripción corta no puede exceder 500 caracteres")
    private String shortDescription;

    @NotNull(message = "El tipo de proyecto es requerido")
    private ProjectType projectType;

    @Size(max = 500, message = "Las tecnologías no pueden exceder 500 caracteres")
    private String technologies;

    private String githubUrl;
    private String demoUrl;
    private String imagePath;
    private String galleryPaths;
    private boolean featured;

    // Constructores
    public ProjectDTO() {}

    public ProjectDTO(String title, String description, String shortDescription,
                      ProjectType projectType, String technologies) {
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.projectType = projectType;
        this.technologies = technologies;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public ProjectType getProjectType() { return projectType; }
    public void setProjectType(ProjectType projectType) { this.projectType = projectType; }

    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getGalleryPaths() { return galleryPaths; }
    public void setGalleryPaths(String galleryPaths) { this.galleryPaths = galleryPaths; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
}

