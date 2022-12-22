package com.wilkwm.pracainz.web.creator;

import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.SaveProjectDto;
import com.wilkwm.pracainz.web.admin.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CreatorProjectController {
    private final ProjectService projectService;
    private final FieldService fieldService;

    public CreatorProjectController(ProjectService projectService, FieldService fieldService) {
        this.projectService = projectService;
        this.fieldService = fieldService;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/creator/add-project")
    public String addProjectForm(Model model){
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field",allField);
        SaveProjectDto project = new SaveProjectDto();
        model.addAttribute("project", project);
        return "project-form";
    }
    @PostMapping("/creator/add-project")
    public String addProject(SaveProjectDto project, RedirectAttributes redirectAttributes){
        projectService.addProject(project);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s saved".formatted(project.getName()));
        return "redirect:/creatorPanel";
    }
}
