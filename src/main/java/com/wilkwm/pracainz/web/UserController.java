package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.commission.CommissionService;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
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

@Controller
public class UserController {

private final UserService userService;
private final ProjectService projectService;
private final CommissionService commissionService;
    public UserController(UserService userService, ProjectService projectService, CommissionService commissionService) {
        this.userService = userService;
        this.projectService = projectService;
        this.commissionService = commissionService;
    }

    private void getUser(@PathVariable String name, Model model) {

        UserDto user = userService.findUserByName(name).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ProjectDto> projects = projectService.findProjectsByCreatorName(name);
        model.addAttribute("heading", user.getName());
        model.addAttribute("description", user.getEmail());
        model.addAttribute("projects", projects);

    }
    @GetMapping("/creator/{name}")
    public String getCreator(@PathVariable String name, Model model) {

        getUser(name, model);
        return "creator/creator-page";
    }

    @GetMapping("creator/{name}/project-list")
    public String getCreatorProjectList(@PathVariable String name, Model model, Authentication authentication) {
        getUser(name, model);
        return "project-listing";
    }

    @GetMapping("creator/project-list")
    public String getCurrentLoggedCreatorProjectList(Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        UserDto user = userService.findInfoByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<ProjectDto> projects = projectService.findProjectsByCreatorName(user.getName());
        model.addAttribute("heading", user.getName());
        model.addAttribute("description", user.getEmail());
        model.addAttribute("projects", projects);
        return "project-listing";
    }

    @GetMapping("creator/commission-list")
    public String getCurrentLoggedCreatorCommissionList(Model model, Authentication authentication) {
        return getCommissionList(model, authentication);
    }

    private String getCommissionList(Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        UserDto user = userService.findInfoByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<CommissionDto> commissions = commissionService.findCommissionsByCreatorName(user.getName());
        model.addAttribute("heading", user.getName());
        model.addAttribute("description", user.getEmail());
        model.addAttribute("commissions", commissions);
        return "commission-listing";
    }

    @GetMapping("creator/{name}/commission-list")
    public String getCreatorCommissionList(@PathVariable String name, Model model) {
        return getNameCommissionList(name, model);
    }

    private String getNameCommissionList(@PathVariable String name, Model model) {
        UserDto user = userService.findUserByName(name).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<CommissionDto> commissions = commissionService.findCommissionsByCreatorName(name);
        model.addAttribute("heading", user.getName());
        model.addAttribute("description", user.getEmail());
        model.addAttribute("commissions", commissions);
        return "commission-listing";
    }

    @GetMapping("employer/commission-list")
    public String getCurrentLoggedEmployerCommissionList(Model model, Authentication authentication) {
        return getCommissionList(model, authentication);
    }

    @GetMapping("employer/{name}/commission-list")
    public String getEmployerCommissionList(@PathVariable String name, Model model) {
        return getNameCommissionList(name, model);
    }

    @GetMapping("/creators")
    public String getCreatorList(Model model){
        List<UserDto> users = userService.findAllRegisteredUsers();
        model.addAttribute("users", users);
        return "users-listing";
    }

}
