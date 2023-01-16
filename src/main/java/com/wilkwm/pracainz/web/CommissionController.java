package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.commission.CommissionService;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.field.FieldService;
import com.wilkwm.pracainz.domain.field.dto.FieldDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommissionController {
    private final CommissionService commissionService;
    private final UserService userService;
    private final FieldService fieldService;


    public CommissionController(CommissionService commissionService, UserService userService, FieldService fieldService) {
        this.commissionService = commissionService;
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @GetMapping("/commission/{id}")
    public String getCommission(@PathVariable long id, Model model) {
        CommissionDto commission = commissionService.findCommissionById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDto user = userService.findUserByName(commission.getUser()).orElseThrow();
        model.addAttribute("userEmail",user.getEmail());
        model.addAttribute("commission", commission);
        return "commission";
    }

    @GetMapping("commission-page")
    public String getCommissionPage(){
        return "commission-page";
    }

    @GetMapping("/creator-commissions")
    public String getCreatorCommissionList(Model model, HttpServletRequest request){
        model.addAttribute("pageName", request.getServletPath());
        List<CommissionDto> commissions = commissionService.findAllAvailableCommissionsByCreator();
        model.addAttribute("commissions", commissions);
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("fields", allField);
        return "commission-listing";
    }

    @GetMapping("/employer-commissions")
    public String getEmployerCommissionList(Model model, HttpServletRequest request){
        model.addAttribute("pageName", request.getServletPath());
        List<CommissionDto> commissions = commissionService.findAllAvailableCommissionsByEmployer();
        model.addAttribute("commissions",commissions);

        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("fields", allField);
        return "commission-listing";
    }

    @GetMapping("/commissions/search")
    public String searchCommissions(@RequestParam(required = false) Long fieldId, @RequestParam(required = false) Integer timeNeeded,
                                    @RequestParam(required = false) BigDecimal pricingTo, @RequestParam(required = false) String keyword,
                                    Model model, HttpServletRequest request) {
        model.addAttribute("pageName", request.getServletPath());
        List<CommissionDto> commissions = commissionService.findAllCommissions();
        List<FieldDto> allField = fieldService.findAllFields();
        model.addAttribute("fields", allField);
        if(fieldId != null){
            FieldDto field = fieldService.findFieldById(fieldId).orElseThrow();
            commissions = commissionService.findCommissionsByFieldName(field.getName());
        }
        if(timeNeeded != null){
            commissions = commissionService.findCommissionsByTimeNeededLessThanEqual(timeNeeded);
        }
        if (pricingTo != null){
            commissions = commissionService.findCommissionsByPriceToLessThanEqual(pricingTo);
        }
        if(keyword != null && !keyword.isEmpty()) {
            commissions = commissionService.findCommissionsByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword);
        }
        model.addAttribute("commissions", commissions);
        return "commission-listing";
    }
}
