package ua.ivanyshen.blogmanagerapi.controller;


import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogRepository;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogService;
import ua.ivanyshen.blogmanagerapi.model.Blog.CreateBlogRequest;
import ua.ivanyshen.blogmanagerapi.model.User.UserService;


@RestController
@RequestMapping("/api/v1")
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        Blog b = blogService.findById(id); //find blog by id
        
        return ResponseEntity.ok(b);
    }

    @PostMapping("/blogs")
    public Blog createBlog(@RequestBody CreateBlogRequest req) {
        //create new blog from a request
        Blog b = new Blog(req.getName(), req.getTopic(), req.getDescription());

        userService.addWritingBlogToList(b);
        
        return blogService.save(b);
    }

    @PostMapping("/blogs/{id}")
    public ResponseEntity<Long> addBlogToFavorites(@PathVariable String id) {
        Blog b = blogService.findById(id);
        
        b.setReadersCount(b.getReadersCount() + 1);

        userService.addReadingBlogToList(b);

        b = blogService.save(b);

        return ResponseEntity.ok(b.getReadersCount());

    }
}
