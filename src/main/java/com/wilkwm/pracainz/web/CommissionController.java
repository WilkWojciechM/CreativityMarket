package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.commission.CommissionService;
import com.wilkwm.pracainz.domain.commission.dto.CommissionDto;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class CommissionController {
    private final CommissionService commissionService;
    private final UserService userService;

    public CommissionController(CommissionService commissionService, UserService userService) {
        this.commissionService = commissionService;
        this.userService = userService;
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

    @GetMapping("/commissions")
    public String getCommissionList(Model model){
        List<CommissionDto> commissions = commissionService.findAllCommissions();
        model.addAttribute("commissions", commissions);
        return "commission-listing";
    }
}
