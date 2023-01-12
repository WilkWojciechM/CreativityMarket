package com.wilkwm.pracainz.domain.favoriteUser;

import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRole;
import com.wilkwm.pracainz.domain.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FavoriteUserController {
    private final FavoriteUserService favoriteUserService;
    private final UserService userService;

    public FavoriteUserController(FavoriteUserService favoriteUserService, UserService userService) {
        this.favoriteUserService = favoriteUserService;
        this.userService = userService;
    }

    @PostMapping("/add-to-favorite-users")
    public String addToFavorites(@RequestParam String favoriteUserEmail,
                                 @RequestHeader String referer,
                                 Authentication authentication) {
        String userEmail = authentication.getName();
        favoriteUserService.addToFavorites(userEmail, favoriteUserEmail);
        return "redirect:" + referer;
    }

    @PostMapping("/remove-from-favorite-users")
    public String removeFromFavorites(@RequestParam String favoriteUserEmail,
                                      @RequestHeader String referer,
                                      Authentication authentication) {
        String userEmail = authentication.getName();
        favoriteUserService.removeFromFavorites(userEmail, favoriteUserEmail);
        return "redirect:" + referer;
    }

    @GetMapping("/favorite-users")
    public String getFavoriteUsers(Model model, Authentication authentication) {
        String userEmail = authentication.getName();

        List<User> favoriteUsers = favoriteUserService.getFavoriteUsers(userEmail);
        model.addAttribute("favoriteUsers", favoriteUsers);

        return "favorite-users";
    }
}