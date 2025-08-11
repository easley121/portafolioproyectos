package com.nekorp.portafolio.proyectos.controller;

import com.nekorp.portafolio.proyectos.dto.ProjectDTO;
import com.nekorp.portafolio.proyectos.entity.Project;
import com.nekorp.portafolio.proyectos.entity.ProjectType;
import com.nekorp.portafolio.proyectos.service.ContactService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("title", "Panel Administrativo - Portfolio");
        model.addAttribute("totalProjects", projectService.getTotalProjects());
        model.addAttribute("unreadMessages", contactService.getUnreadCount());
        model.addAttribute("recentProjects", projectService.getAllProjects().stream().limit(5).toList());
        return "pages/admin/dashboard";
    }

    @GetMapping("/projects")
    public String listProjectsAdmin(Model model) {
        model.addAttribute("title", "Administrar Proyectos - Portfolio");
        model.addAttribute("projects", projectService.getAllProjects());
        return "pages/admin/projects";
    }

    @GetMapping("/projects/new")
    public String newProjectForm(Model model) {
        model.addAttribute("title", "Nuevo Proyecto - Portfolio");
        model.addAttribute("projectDTO", new ProjectDTO());
        model.addAttribute("projectTypes", ProjectType.values());
        model.addAttribute("isEdit", false);
        return "pages/admin/project-form";
    }

    @GetMapping("/projects/{id}/edit")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Optional<Project> project = projectService.getProjectById(id);

        if (project.isEmpty()) {
            return "redirect:/admin/projects";
        }

        // Convertir entidad a DTO
        Project p = project.get();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(p.getId());
        projectDTO.setTitle(p.getTitle());
        projectDTO.setDescription(p.getDescription());
        projectDTO.setShortDescription(p.getShortDescription());
        projectDTO.setProjectType(p.getProjectType());
        projectDTO.setTechnologies(p.getTechnologies());
        projectDTO.setGithubUrl(p.getGithubUrl());
        projectDTO.setDemoUrl(p.getDemoUrl());
        projectDTO.setImagePath(p.getImagePath());
        projectDTO.setGalleryPaths(p.getGalleryPaths());
        projectDTO.setFeatured(p.isFeatured());

        model.addAttribute("title", "Editar Proyecto - Portfolio");
        model.addAttribute("projectDTO", projectDTO);
        model.addAttribute("projectTypes", ProjectType.values());
        model.addAttribute("isEdit", true);
        return "pages/admin/project-form";
    }

    @PostMapping("/projects")
    public String saveProject(@Valid @ModelAttribute ProjectDTO projectDTO,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", projectDTO.getId() == null ?
                    "Nuevo Proyecto - Portfolio" : "Editar Proyecto - Portfolio");
            model.addAttribute("projectTypes", ProjectType.values());
            model.addAttribute("isEdit", projectDTO.getId() != null);
            return "pages/admin/project-form";
        }

        try {
            // Convertir DTO a entidad
            Project project;
            if (projectDTO.getId() != null) {
                // Editar proyecto existente
                Optional<Project> existingProject = projectService.getProjectById(projectDTO.getId());
                if (existingProject.isEmpty()) {
                    redirectAttributes.addFlashAttribute("errorMessage", "Proyecto no encontrado.");
                    return "redirect:/admin/projects";
                }
                project = existingProject.get();
            } else {
                // Crear nuevo proyecto
                project = new Project();
            }

            // Mapear datos del DTO a la entidad
            project.setTitle(projectDTO.getTitle());
            project.setDescription(projectDTO.getDescription());
            project.setShortDescription(projectDTO.getShortDescription());
            project.setProjectType(projectDTO.getProjectType());
            project.setTechnologies(projectDTO.getTechnologies());
            project.setGithubUrl(projectDTO.getGithubUrl());
            project.setDemoUrl(projectDTO.getDemoUrl());
            project.setImagePath(projectDTO.getImagePath());
            project.setGalleryPaths(projectDTO.getGalleryPaths());
            project.setFeatured(projectDTO.isFeatured());

            projectService.saveProject(project);

            redirectAttributes.addFlashAttribute("successMessage",
                    projectDTO.getId() == null ?
                            "Proyecto creado exitosamente." : "Proyecto actualizado exitosamente.");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error al guardar el proyecto: " + e.getMessage());
        }

        return "redirect:/admin/projects";
    }

    @PostMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            projectService.deleteProject(id);
            redirectAttributes.addFlashAttribute("successMessage", "Proyecto eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el proyecto.");
        }

        return "redirect:/admin/projects";
    }

    @GetMapping("/contacts")
    public String listContacts(Model model) {
        model.addAttribute("title", "Mensajes de Contacto - Portfolio");
        model.addAttribute("contacts", contactService.getAllContacts());
        model.addAttribute("unreadCount", contactService.getUnreadCount());
        return "pages/admin/contacts";
    }

    @PostMapping("/contacts/{id}/mark-read")
    public String markContactAsRead(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            contactService.markAsRead(id);
            redirectAttributes.addFlashAttribute("successMessage", "Mensaje marcado como leído.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el mensaje.");
        }

        return "redirect:/admin/contacts";
    }

    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            contactService.deleteContact(id);
            redirectAttributes.addFlashAttribute("successMessage", "Mensaje eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el mensaje.");
        }

        return "redirect:/admin/contacts";
    }
}
