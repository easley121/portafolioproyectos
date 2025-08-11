package com.nekorp.portafolio.proyectos.controller;

import com.nekorp.portafolio.proyectos.dto.ProjectDTO;
import com.nekorp.portafolio.proyectos.entity.Project;
import com.nekorp.portafolio.proyectos.entity.ProjectType;
import com.nekorp.portafolio.proyectos.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String listProjects(@RequestParam(value = "type", required = false) ProjectType type,
                               @RequestParam(value = "search", required = false) String search,
                               Model model) {
        model.addAttribute("title", "Proyectos - Portfolio");
        model.addAttribute("projectTypes", ProjectType.values());

        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("projects", projectService.searchProjects(search));
            model.addAttribute("searchTerm", search);
        } else if (type != null) {
            model.addAttribute("projects", projectService.getProjectsByType(type));
            model.addAttribute("selectedType", type);
        } else {
            model.addAttribute("projects", projectService.getAllProjects());
        }

        return "pages/projects";
    }

    @GetMapping("/{id}")
    public String projectDetail(@PathVariable Long id, Model model) {
        Optional<Project> project = projectService.getProjectById(id);

        if (project.isEmpty()) {
            return "redirect:/projects";
        }

        model.addAttribute("title", project.get().getTitle() + " - Portfolio");
        model.addAttribute("project", project.get());
        return "pages/project-detail";
    }
}