package ua.ivanyshen.blogmanager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.ivanyshen.blogmanager.Models.Blog.Blog;
import ua.ivanyshen.blogmanager.Models.Blog.BlogRepo;
import ua.ivanyshen.blogmanager.Models.User.User;
import ua.ivanyshen.blogmanager.Models.User.UserRepo;

@Controller
public class BlogController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BlogRepo repo;
    private String message = "";
    

    @GetMapping("/create-blog")
    public String createBlog(Model model) {
        model.addAttribute("username", MainController.username);
        model.addAttribute("blog", new Blog());
        model.addAttribute("message", message);
        return "createBlog";
    }

    @PostMapping("/create-blog")
    public String blogCreated(@ModelAttribute Blog blog) {
        Blog foundByName = repo.findByName(blog.getName());
        if(foundByName != null) {
            message = "Blog name is already taken";
            return "redirect:/create-blog";
        }
        User user = userRepo.findByUsername(MainController.username);
        user.addWritingBlog(blog.getId());
        userRepo.save(user);
        repo.save(blog);
        return "redirect:/";
    }
}
