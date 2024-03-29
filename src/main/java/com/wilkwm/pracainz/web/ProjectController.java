package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.rating.RatingService;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProjectController {
    private final ProjectService projectService;
    private final RatingService ratingService;

    private final UserService userService;

    public ProjectController(ProjectService projectService, RatingService ratingService, UserService userService) {
        this.projectService = projectService;
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @GetMapping("/project/{id}")
    public String getProject(@PathVariable long id, Model model, Authentication authentication) {
        ProjectDto project = projectService.findProjectById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("project", project);

        UserDto user = userService.findUserByName(project.getUser()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("userEmail",user.getEmail());
        String userEmail = "";

        if(authentication != null) {
            userEmail = authentication.getName();
            Integer rating = ratingService.getUserRating(userEmail, id).orElse(0);
            model.addAttribute("userRating", rating);

        }
        return "project";
    }

    @GetMapping("/sortProjects")
    public String sortProjects(@RequestParam("order") String order, Model model) {
        List<ProjectDto> projects = new ArrayList<>(projectService.findAllProjects());
        if (order.equals("asc")) {
            projects.sort(Comparator.comparingDouble(ProjectDto::getAvgRating));
        } else {
            projects.sort(Comparator.comparingDouble(ProjectDto::getAvgRating).reversed());
        }
        model.addAttribute("projects", projects);
        return "project-listing";
    }
}
