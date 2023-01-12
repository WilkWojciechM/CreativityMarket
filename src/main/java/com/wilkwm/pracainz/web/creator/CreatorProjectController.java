package com.wilkwm.pracainz.web.creator;

import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.Project;
import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.project.dto.SaveProjectDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import com.wilkwm.pracainz.web.admin.AdminController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CreatorProjectController {
    private final ProjectService projectService;
    private final FieldService fieldService;

    private final UserService userService;

    public CreatorProjectController(ProjectService projectService, FieldService fieldService, UserService userService) {
        this.projectService = projectService;
        this.fieldService = fieldService;
        this.userService = userService;
    }


    @GetMapping("/creator/add-project")
    public String addProjectForm(Model model, Authentication authentication) {
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field", allField);
        SaveProjectDto project = new SaveProjectDto();

        if (authentication != null) {
            String userEmail = authentication.getName();
            Optional<UserDto> user = userService.findInfoByEmail(userEmail);
            String userName = "";
            if (user.isPresent()) {
                userName = user.get().getName();
            }
            project.setUser(userName);
        }
        model.addAttribute("project", project);
        return "project-form";
    }

    @PostMapping("/creator/add-project")
    public String addProject(SaveProjectDto project, RedirectAttributes redirectAttributes) {
        projectService.addProject(project);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s saved".formatted(project.getName()));
        return "redirect:/creator-panel";
    }

    @GetMapping("/creator/edit-project/{id}")
    public String editProjectForm(@PathVariable Long id, Model model, Authentication authentication) {
       // long projectId = Long.parseLong(id);
        ProjectDto project = projectService.findProjectById(id).orElseThrow();

        UserDto user = userService.findUserByName(project.getUser()).orElseThrow();
        model.addAttribute("userEmail",user.getEmail());

        ProjectDto projectDto = new ProjectDto(project.getId(), project.getName(), project.getField(), project.getUser(), project.isPromoted(), project.getDescription(), project.getYoutubeId(), project.getProjectPic(), project.getAvgRating(), project.getRatingCount());
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field", allField);
        model.addAttribute("project", projectDto);

        return "edit-project-form";
    }

    @PostMapping("/creator/edit-project/{id}")
    public String editProject(@PathVariable Long id, SaveProjectDto project, RedirectAttributes redirectAttributes) {
        projectService.updateProject(id, project);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s updated".formatted(project.getName()));
        return "redirect:/creator-panel";
    }

    @GetMapping("/creator/delete-project/{id}")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes, Authentication authentication) {
        // Only allow the logged-in user to delete their own projects
        ProjectDto project = projectService.findProjectById(id).orElseThrow();
        UserDto user = userService.findUserByName(project.getUser()).orElseThrow();
        if (!user.getEmail().equals(authentication.getName())) {
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "You are not authorized to delete this project.");
            return "redirect:/creator-panel";
        }
        projectService.deleteProject(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s deleted".formatted(project.getName()));
        return "redirect:/creator-panel";
    }
}
