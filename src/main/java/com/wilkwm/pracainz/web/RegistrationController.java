package com.wilkwm.pracainz.web;

import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserRegistrationDto userRegistrationDto,
                               @RequestParam(value = "isEmployer", required = false) boolean isEmployer,
                               @RequestParam(value = "isCreator", required = false) boolean isCreator,
                               Model model) {
        if (isEmployer) {
            userService.registerUser(userRegistrationDto, "EMPLOYER");
        } else if (isCreator) {
            userService.registerUser(userRegistrationDto, "CREATOR");
        } else {
            model.addAttribute("error", "You must select a role");
            return "registration";
        }
        return "redirect:/login";
    }
}