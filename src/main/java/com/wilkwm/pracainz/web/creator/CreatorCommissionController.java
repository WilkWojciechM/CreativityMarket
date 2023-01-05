package com.wilkwm.pracainz.web.creator;

import com.wilkwm.pracainz.domain.commission.CommissionService;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.project.dto.SaveProjectDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import com.wilkwm.pracainz.web.admin.AdminController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "redirect:/creatorPanel";
    }
}
