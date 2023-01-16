package com.wilkwm.pracainz.web.admin;


import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FieldAdminController {
    private final FieldService fieldService;

    public FieldAdminController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/admin/add-field")
    public String addFieldForm(Model model){
        FieldDto fieldDto = new FieldDto();
        model.addAttribute("field", fieldDto);
        return "admin/field-form";
    }


    @PostMapping("/admin/add-field")
    public String addField(FieldDto field, RedirectAttributes redirectAttributes){
        fieldService.addField(field);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Field %s added".formatted(field.getName()));
        return "redirect:/admin";
    }
}
