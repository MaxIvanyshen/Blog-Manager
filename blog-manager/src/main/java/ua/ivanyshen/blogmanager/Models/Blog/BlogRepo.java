package ua.ivanyshen.blogmanager.Models.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, String> {
    
    public Blog findByName(String name);
}
