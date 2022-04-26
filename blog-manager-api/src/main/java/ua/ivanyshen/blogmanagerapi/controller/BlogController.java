package ua.ivanyshen.blogmanagerapi.controller;


import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogRepository;
import ua.ivanyshen.blogmanagerapi.model.Blog.CreateBlogRequest;


@RestController
@RequestMapping("/api/v1")
public class BlogController {

    private final BlogRepository blogRepo;

    @Autowired
    public BlogController(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    //get blog by id rest api
    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        Blog b = blogRepo.findById(id) //find blog by id or throw a ResourceNotFoundException if blog is not found
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        
        //return blog as a response
        return ResponseEntity.ok(b);
    }

    //create blog rest api
    @PostMapping("/blogs")
    public Blog createBlog(@RequestBody CreateBlogRequest req) {
        //create new blog from a request
        Blog b = new Blog(req.getName(), req.getTopic(), req.getDescription());
        
        //return newly created blog
        return blogRepo.save(b);
    }
}
