package ua.ivanyshen.blogmanagerapi.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User save(User u) {
        return repo.save(u);
    }

    public void delete(User u) {
        repo.deleteById(u.getId());
    }

    public User findById(String id) {
        User found = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return found;
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
