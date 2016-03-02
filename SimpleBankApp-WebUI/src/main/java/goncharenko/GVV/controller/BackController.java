package goncharenko.GVV.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/back")
public class BackController {
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model){
        return "index";
    }
}
