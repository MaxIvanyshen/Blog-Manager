package ua.ivanyshen.blogmanager.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainController {
    public static String username = "";

    @GetMapping("/")
    public String index(Model model) {     
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        if(username.equals("anonymousUser")) {
            username = "";
        }
        model.addAttribute("username", username);
        return "index";
    }
    
    @GetMapping("/user/{id}/home")
    public String homepage(@PathVariable("id") String id, Model model) {
        model.addAttribute("username", username);
        return "homepage";
    }

    
}
