package ua.ivanyshen.blogmanagerapi.model.Blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;

@Service
public class BlogService {

    private final BlogRepository blogRepo;

    @Autowired
    public BlogService(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    public Blog save(Blog b) {
        return blogRepo.save(b);
    }
    
    public void deleteById(String id) {
        blogRepo.deleteById(id);
    }
    
    public Blog findById(String id) {
        Blog found = blogRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        return found;
    }
    
    public Blog findByName(String name) {
        return blogRepo.findByName(name);
    }    
}
