package ua.ivanyshen.blogmanager.Models.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog, String> {
    
    public Blog findByName(String name);

}
