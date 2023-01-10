package com.wilkwm.pracainz.web.employer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployerController {
    @GetMapping("/employerPanel")
    public String getCreatorPanel(){
        return "employer/employer-panel";
    }

    @GetMapping("/employerPage")
    public String getCreatorPage(){return "employer/employer-page";}
}
