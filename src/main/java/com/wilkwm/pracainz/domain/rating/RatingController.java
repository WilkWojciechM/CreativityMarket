package com.wilkwm.pracainz.domain.rating;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate-project")
    public String addRating(@RequestParam long projectId,
                            @RequestParam int rating,
                            @RequestHeader String referer,
                            Authentication authentication){
        String userEmail = authentication.getName();
        ratingService.addRating(userEmail, projectId, rating);
        return "redirect:" + referer;
    }
}
