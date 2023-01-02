package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.rating.RatingService;
import com.wilkwm.pracainz.domain.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class ProjectController {
    private final ProjectService projectService;
    private final RatingService ratingService;

    public ProjectController(ProjectService projectService, RatingService ratingService) {
        this.projectService = projectService;
        this.ratingService = ratingService;
    }

    @GetMapping("/project/{id}")
    public String getProject(@PathVariable long id, Model model, Authentication authentication) {
        ProjectDto project = projectService.findProjectById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("project", project);
        if(authentication != null) {
            String userEmail = authentication.getName();
            Integer rating = ratingService.getUserRating(userEmail, id).orElse(0);
            model.addAttribute("userRating", rating);

        }
        return "project";
    }
}
