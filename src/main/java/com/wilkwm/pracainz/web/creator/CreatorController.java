package com.wilkwm.pracainz.web.creator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreatorController {
    @GetMapping("/creator-panel")
    public String getCreatorPanel(){
        return "creator/creatorPanel";
    }

    @GetMapping("/creator-page")
    public String getCreatorPage(){return "creator/creator-page";}
}
