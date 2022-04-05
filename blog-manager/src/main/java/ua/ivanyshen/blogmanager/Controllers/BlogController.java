package ua.ivanyshen.blogmanager.Controllers;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import ua.ivanyshen.blogmanager.Models.Blog.Blog;
import ua.ivanyshen.blogmanager.Models.Blog.BlogRepo;
import ua.ivanyshen.blogmanager.Models.User.User;
import ua.ivanyshen.blogmanager.Models.User.UserRepo;

@Controller
public class BlogController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BlogRepo repo;

    private String message = "";
    

    @GetMapping("/create-blog")
    public String createBlog(Model model) {
        model.addAttribute("username", MainController.username);
        model.addAttribute("blog", new Blog());
        model.addAttribute("message", message);
        return "createBlog";
    }

    @PostMapping("/create-blog")
    public String blogCreated(@PathVariable("icon") MultipartFile icon, 
                                @PathVariable("banner") MultipartFile banner,
                                @ModelAttribute Blog blog) {
        Blog foundByName = repo.findByName(blog.getName());
        if(foundByName != null) {
            message = "Blog name is already taken";
            return "redirect:/create-blog";
        }
        
        try {
            byte iconBytes[] = icon.getBytes();
            blog.setIcon(iconBytes);

            byte bannerBytes[] = banner.getBytes();
            blog.setBanner(bannerBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        User user = userRepo.findByUsername(MainController.username);
        user.addWritingBlog(blog.getName());
        userRepo.save(user);
        repo.save(blog);
        return "redirect:/";
    }

    @GetMapping("/saveBlogIcon")
    public String saveBlogIcon(Model model) {
        model.addAttribute("img", Base64.getEncoder().encodeToString(repo.findByName("asdada").getIcon()));

        return "img";
    }

    
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		
	}
}
