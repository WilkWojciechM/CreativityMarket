package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ProjectService projectService;

    public HomeController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ProjectDto> promotedProject = projectService.findAllPromotedProjects();
        model.addAttribute("heading", "Promoted projects");
        model.addAttribute("description", "Top rated projects by our freelancers");
        model.addAttribute("projects", promotedProject);
        return "project-listing";
    }
}
