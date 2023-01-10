package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.favoritePList.FavoriteProjectService;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

        UserDto user = userService.findUserByName(project.getUser()).orElseThrow();
        model.addAttribute("userEmail",user.getEmail());
        String userEmail = authentication.getName();

        if(authentication != null) {
            userEmail = authentication.getName();
            Integer rating = ratingService.getUserRating(userEmail, id).orElse(0);
            model.addAttribute("userRating", rating);

        }
        return "project";
    }
}
