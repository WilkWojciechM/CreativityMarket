package com.wilkwm.pracainz.web.employer;

import com.wilkwm.pracainz.domain.commission.CommissionService;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import com.wilkwm.pracainz.web.admin.AdminController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployerCommissionController {
    private final CommissionService commissionService;
    private final FieldService fieldService;
    private final UserService userService;


    public EmployerCommissionController(CommissionService projectService, FieldService fieldService, UserService userService) {
        this.commissionService = projectService;
        this.fieldService = fieldService;
        this.userService= userService;
    }
    @GetMapping("/employer/add-commission")
    public String addCommissionForm(Model model, Authentication authentication){
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field",allField);
        CommissionDto commission = new CommissionDto();
        commission.setJobOffer(true);
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
        return "commission-employer-form";
    }
    @PostMapping("/employer/add-commission")
    public String addCommission(CommissionDto commission, RedirectAttributes redirectAttributes){
        commission.setJobOffer(true);
        commissionService.addCommission(commission);
        System.out.println(commission.isJobOffer());
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Commission %s saved".formatted(commission.getName()));
        return "redirect:/employerPanel";
    }

    @GetMapping("/employer/edit-commission/{id}")
    public String editCommissionForm(@PathVariable Long id, Model model) {
        CommissionDto commission = commissionService.findCommissionById(id).orElseThrow();
        UserDto user = userService.findUserByName(commission.getUser()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("field", allField);

        model.addAttribute("commission", commission);
        model.addAttribute("userEmail",user.getEmail());
        return "edit-commission-form";
    }

    @PostMapping("/employer/edit-commission/{id}")
    public String editCommission(@PathVariable Long id, CommissionDto commission, RedirectAttributes redirectAttributes) {

        commissionService.updateCommission(id, commission);
        redirectAttributes.addFlashAttribute("notification", "Commission updated successfully.");
        return "redirect:/employerPanel";
    }

    @GetMapping("/employer/delete-commission/{id}")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes, Authentication authentication) {
        // Only allow the logged-in user to delete their own projects
        CommissionDto commission = commissionService.findCommissionById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDto user = userService.findUserByName(commission.getUser()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!user.getEmail().equals(authentication.getName())) {
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "You are not authorized to delete this project.");
            return "redirect:/employerPage";
        }
        commissionService.deleteCommission(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Project %s deleted".formatted(commission.getName()));
        return "redirect:/employerPage";
    }


}
