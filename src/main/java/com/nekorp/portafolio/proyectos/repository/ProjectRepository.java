package com.nekorp.portafolio.proyectos.repository;

import com.nekorp.portafolio.proyectos.entity.Project;
import com.nekorp.portafolio.proyectos.entity.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByFeaturedTrue();

    List<Project> findByProjectType(ProjectType projectType);

    @Query("SELECT p FROM Project p ORDER BY p.createdAt DESC")
    List<Project> findAllOrderByCreatedAtDesc();

    @Query("SELECT p FROM Project p WHERE p.technologies LIKE %?1%")
    List<Project> findByTechnologyContaining(String technology);

    @Query("SELECT p FROM Project p WHERE p.title LIKE %?1% OR p.description LIKE %?1%")
    List<Project> searchByTitleOrDescription(String searchTerm);
}


