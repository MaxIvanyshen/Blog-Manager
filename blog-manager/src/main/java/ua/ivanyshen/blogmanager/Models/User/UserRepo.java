package ua.ivanyshen.blogmanager.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    
    public User findByUsername(String username);

    public User findByEmail(String email);
}
