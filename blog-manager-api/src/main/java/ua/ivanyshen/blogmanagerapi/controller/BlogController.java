package ua.ivanyshen.blogmanagerapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogRepository;

@RestController
@RequestMapping("/api/v1/blogs")
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
}
