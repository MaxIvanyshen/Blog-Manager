package ua.ivanyshen.blogmanager.Controllers;

import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.ivanyshen.blogmanager.Models.Blog.Blog;
import ua.ivanyshen.blogmanager.Models.Blog.BlogRepo;
import ua.ivanyshen.blogmanager.Models.User.User;
import ua.ivanyshen.blogmanager.Models.User.UserRepo;


@Controller
public class MainController {
    public static String username = "";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    BlogRepo blogRepo;



    @GetMapping("/")
    public String index(Model model) {     
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
        if(!username.equals("anonymousUser")) {
            User user = userRepo.findByUsername(username);
            model.addAttribute("username", username);

            //include the list of writing blogs
            model.addAttribute("writingBlogsList", getWritingBlogsList(user));
            model.addAttribute("readingBlogsList", getReadingBlogsList(user));
            
            ArrayList<String> writingIconList = new ArrayList<>();
            for(Blog b : getWritingBlogsList(user)) {
                if(b == null) {
                    break;
                }
                writingIconList.add(Base64.getEncoder().encodeToString(b.getIcon()));
            }

            ArrayList<String> writingBannerList = new ArrayList<>();
            for(Blog b : getWritingBlogsList(user)) {
                if(b == null) {
                    break;
                }
                writingBannerList.add(Base64.getEncoder().encodeToString(b.getBanner()));
            }
            
            
            ArrayList<String> readingIconList = new ArrayList<>();
            for(Blog b : getReadingBlogsList(user)) {
                if(b == null) {
                    break;
                }
                readingIconList.add(Base64.getEncoder().encodeToString(b.getIcon()));
            }

            ArrayList<String> readingBannerList = new ArrayList<>();
            for(Blog b : getReadingBlogsList(user)) {
                if(b == null) {
                    break;
                }
                readingBannerList.add(Base64.getEncoder().encodeToString(b.getBanner()));
            }
            
            //include writing and reading blogs icons and banners into the page
            model.addAttribute("writingIconList", writingIconList);
            model.addAttribute("writingBannerList", writingBannerList);
            model.addAttribute("readingIconList", readingIconList);
            model.addAttribute("readingBannerList", readingBannerList);
            
            return "homepage";
        }
        username = "";
        model.addAttribute("username", username);
        return "index";
    }
    
    public ArrayList<Blog> getWritingBlogsList(User user) {
        ArrayList<Blog> list = new ArrayList<>();
        for(String name : user.getWritingBlogs()) {
            Blog b = blogRepo.findByName(name); 
            list.add(b);
        } 
        return list;
    }

    public ArrayList<Blog> getReadingBlogsList(User user) {
        ArrayList<Blog> list = new ArrayList<>();
        for(String name : user.getReadingBlogs()) {
            Blog b = blogRepo.findByName(name); 
            list.add(b);
        } 
        return list;
    }

    
}

