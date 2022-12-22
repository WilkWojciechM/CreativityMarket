package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class UserController {

private final UserService userService;
private final ProjectService projectService;

    public UserController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/creator/{name}")
    public String getCreator(@PathVariable String name, Model model) {
        UserDto user = userService.findUserByName(name).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ProjectDto> projects = projectService.findProjectsByCreatorName(name);
        model.addAttribute("heading", user.getName());
        model.addAttribute("description", user.getEmail());
        model.addAttribute("projects", projects);
        return "project-listing";
    }

    @GetMapping("/creators")
    public String getCreatorList(Model model){
        List<UserDto> users = userService.findAllRegisteredUsers();
        model.addAttribute("users", users);
        return "users-listing";
    }

}
