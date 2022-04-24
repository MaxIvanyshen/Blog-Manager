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

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        Blog b = blogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        return ResponseEntity.ok(b);
    }

    @PostMapping("/blogs")
    public Blog createBlog(@RequestBody CreateBlogRequest req) {
        Blog b = new Blog(req.getName(), req.getTopic(), req.getDescription());
        return blogRepo.save(b);
    }
}
