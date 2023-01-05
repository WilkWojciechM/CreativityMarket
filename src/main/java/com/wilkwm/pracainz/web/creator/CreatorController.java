package com.wilkwm.pracainz.web.creator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreatorController {
    @GetMapping("/creatorPanel")
    public String getCreatorPanel(){
        return "creator/creatorPanel";
    }

    @GetMapping("/creatorPage")
    public String getCreatorPage(){return "creator/creator-page";}
}
