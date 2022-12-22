package com.wilkwm.pracainz.web.creator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreatorController {

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    @GetMapping("/creatorPanel")
    public String getCreatorPanel(){
        return "creator/creatorPanel";
    }
}
