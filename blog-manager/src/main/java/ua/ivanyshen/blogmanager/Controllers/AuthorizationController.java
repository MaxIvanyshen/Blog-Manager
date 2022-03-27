package ua.ivanyshen.blogmanager.Controllers;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import ua.ivanyshen.blogmanager.Models.User.User;
import ua.ivanyshen.blogmanager.Models.User.UserRepo;

@Controller
public class AuthorizationController {
    
    @Autowired
    private UserRepo repo;
    private String message = "";

    @GetMapping("/signup")
    public String signup(Model model) {
        if(!MainController.username.equals("")) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        model.addAttribute("pass1", new String());
        model.addAttribute("pass2", new String());
        model.addAttribute("message", message);
        return "signup";
    }

    @PostMapping("/signup")
    public String signedUp(@ModelAttribute User user) {

        User foundByEmail = repo.findByEmail(user.getEmail());
        if(foundByEmail != null) {
            message = "Email already taken";
            return "redirect:/signup";
        }

        User foundByUsername = repo.findByUsername(user.getUsername());
        if(foundByUsername != null) {
            message = "Username already taken";
            return "redirect:/signup";
        }

        if(user.getPass1().equals(user.getPass2())) {
            user.setPassword(user.getPass2());
        }
        else {
            message = "Passwords do not match";
            return "redirect:/signup";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        System.out.println("User saved!");
        return "redirect:/";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		
	}
}
