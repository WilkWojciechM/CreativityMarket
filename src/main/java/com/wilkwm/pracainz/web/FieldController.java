package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.field.Field;
import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.ProjectService;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class FieldController {
    private final FieldService fieldService;
    private final ProjectService  projectService;

    public FieldController(FieldService fieldService, ProjectService projectService) {
        this.fieldService = fieldService;
        this.projectService = projectService;
    }

    @GetMapping("/field/{name}")
    public String getField(@PathVariable String name, Model model){
        FieldDto field = fieldService.findFieldByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ProjectDto> projects = projectService.findProjectByFieldName(name);
        model.addAttribute("heading", field.getName());
        model.addAttribute("description", field.getDescription());
        model.addAttribute("projects", projects);
        return "project-listing";
    }

    @GetMapping("/fields")
    public String getFieldList(Model model){
        List<FieldDto> fields = fieldService.findAllFields();
        model.addAttribute("fields", fields);
        return "field-listing";
    }
}
