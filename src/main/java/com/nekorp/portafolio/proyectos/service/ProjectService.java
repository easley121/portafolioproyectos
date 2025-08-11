package com.nekorp.portafolio.proyectos.service;

import com.nekorp.portafolio.proyectos.entity.Project;
import com.nekorp.portafolio.proyectos.entity.ProjectType;
import com.nekorp.portafolio.proyectos.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAllOrderByCreatedAtDesc();
    }

    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue();
    }

    public List<Project> getProjectsByType(ProjectType type) {
        return projectRepository.findByProjectType(type);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> searchProjects(String searchTerm) {
        return projectRepository.searchByTitleOrDescription(searchTerm);
    }

    public List<Project> getProjectsByTechnology(String technology) {
        return projectRepository.findByTechnologyContaining(technology);
    }

    public long getTotalProjects() {
        return projectRepository.count();
    }
}
