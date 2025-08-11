package com.nekorp.portafolio.proyectos.controller;

import com.nekorp.portafolio.proyectos.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Portfolio - Easley");
        model.addAttribute("featuredProjects", projectService.getFeaturedProjects());
        model.addAttribute("totalProjects", projectService.getTotalProjects());
        return "pages/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Sobre Mí - Portfolio");
        return "pages/about";
    }
}

