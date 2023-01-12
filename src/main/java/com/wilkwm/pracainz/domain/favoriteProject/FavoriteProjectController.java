package com.wilkwm.pracainz.domain.favoriteProject;


import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class FavoriteProjectController {
    private final FavoriteProjectService favoriteProjectService;
    private final UserService userService;

    public FavoriteProjectController(FavoriteProjectService favoriteProjectService, UserService userService) {
        this.favoriteProjectService = favoriteProjectService;
        this.userService = userService;
    }
    @PostMapping("/add-to-favorites")
    public String addToFavorites(@RequestParam long projectId,
                                 @RequestHeader String referer,
                                 Authentication authentication) {
        String userEmail = authentication.getName();
        favoriteProjectService.addToFavorites(userEmail, projectId);
        return "redirect:" + referer;
    }

    @PostMapping("/remove-from-favorites")
    public String removeFromFavorites(@RequestParam long projectId,
                                      @RequestHeader String referer,
                                      Authentication authentication) {
        String userEmail = authentication.getName();
        favoriteProjectService.removeFromFavorites(userEmail, projectId);
        return "redirect:" + referer;
    }

    @GetMapping("/favorite-projects")
    public String getFavoriteProjects(Model model, Authentication authentication) {
        String userEmail = authentication.getName();

        List<Project> favoriteProjects = favoriteProjectService.getFavoriteProjects(userEmail);
        model.addAttribute("favoriteProjects", favoriteProjects);
        return "favorite-projects";
    }

}
