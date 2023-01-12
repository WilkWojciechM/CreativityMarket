package com.wilkwm.pracainz.web.creator;

import com.wilkwm.pracainz.domain.commission.CommissionService;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.dto.ProjectDto;
import com.wilkwm.pracainz.domain.project.dto.SaveProjectDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import com.wilkwm.pracainz.web.admin.AdminController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
@Controller
public class CreatorCommissionController {
    private final CommissionService commissionService;
    private final FieldService fieldService;
    private final UserService userService;


    public CreatorCommissionController(CommissionService projectService, FieldService fieldService, UserService userService) {
        this.commissionService = projectService;
        this.fieldService = fieldService;
        this.userService= userService;
    }

    @GetMapping("/creator/add-commission")
    public String addCommissionForm(Model model, Authentication authentication){
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field",allField);
        CommissionDto commission = new CommissionDto();

        if (authentication != null) {
            String userEmail = authentication.getName();
            Optional<UserDto> user = userService.findInfoByEmail(userEmail);
            String userName = "";
            if(user.isPresent()) {
                userName = user.get().getName();
            }
            commission.setUser(userName);
        }
        model.addAttribute("commission", commission);
        return "commission-form";
    }
    @PostMapping("/creator/add-commission")
    public String addCommission(CommissionDto commission, RedirectAttributes redirectAttributes){
        commissionService.addCommission(commission);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Commission %s saved".formatted(commission.getName()));
        return "redirect:/creator-panel";
    }

    @GetMapping("/creator/edit-commission/{id}")
    public String editCommissionForm(@PathVariable Long id, Model model) {
        CommissionDto commission = commissionService.findCommissionById(id).orElseThrow();
        UserDto user = userService.findUserByName(commission.getUser()).orElseThrow();
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field", allField);

        model.addAttribute("commission", commission);
        model.addAttribute("userEmail",user.getEmail());
        return "edit-commission-form";
    }

    @PostMapping("/creator/edit-commission/{id}")
    public String editCommission(@PathVariable Long id, CommissionDto commission, RedirectAttributes redirectAttributes) {

        commissionService.updateCommission(id, commission);
        redirectAttributes.addFlashAttribute("notification", "Commission updated successfully.");
        return "redirect:/creator-panel";
    }

    @GetMapping("/creator/delete-commission/{id}")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes, Authentication authentication) {
        // Only allow the logged-in user to delete their own projects
        CommissionDto commission = commissionService.findCommissionById(id).orElseThrow();
        UserDto user = userService.findUserByName(commission.getUser()).orElseThrow();
        if (!user.getEmail().equals(authentication.getName())) {
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "You are not authorized to delete this project.");
            return "redirect:/creator-page";
        }
        commissionService.deleteCommission(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s deleted".formatted(commission.getName()));
        return "redirect:/creator-page";
    }


}
