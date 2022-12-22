package com.wilkwm.pracainz.web.admin;

import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.SaveProjectDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProjectManagementController {
    private final ProjectService projectService;
    private final FieldService fieldService;
    private final UserService userService;

    public ProjectManagementController(ProjectService projectService, FieldService fieldService, UserService userService) {
        this.projectService = projectService;
        this.fieldService = fieldService;
        this.userService = userService;
    }

    @GetMapping("/admin/add-project")
    public String addProjectForm(Model model){
        List<FieldDto> allField = fieldService.findAllFields();
        List<UserDto>  allUsers = userService.findAllRegisteredUsers();
        model.addAttribute("field",allField);
        model.addAttribute("user", allUsers);
        SaveProjectDto project = new SaveProjectDto();
        model.addAttribute("project", project);
        return "project-form";
    }

    @PostMapping("admin/add-project")
    public String addProject(SaveProjectDto project, RedirectAttributes redirectAttributes){
        projectService.addProject(project);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s saved".formatted(project.getName()));
                return "redirect:/admin";
    }

}
