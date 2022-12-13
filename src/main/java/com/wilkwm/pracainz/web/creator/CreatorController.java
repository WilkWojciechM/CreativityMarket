package com.wilkwm.pracainz.web.creator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreatorController {

    @GetMapping("/creator")
    public String getCreatorPanel() {
        return "/creator";
    }
}
