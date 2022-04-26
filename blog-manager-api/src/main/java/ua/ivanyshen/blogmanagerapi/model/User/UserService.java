package ua.ivanyshen.blogmanagerapi.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;

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

    public void addWritingBlogToList(Blog b) {
        User currentUser = getCurrentUser();
        currentUser.addWritingBlogId(b.getId());
        save(currentUser);
    }

    public void addReadingBlogToList(Blog b) {
        User currentUser = getCurrentUser();
        currentUser.addReadingBlogId(b.getId());
        save(currentUser);
    }

    public String getCurrentUserUsername() {
        //get current user data
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";

        boolean userLoggedIn = principal instanceof UserDetails;

        if (userLoggedIn) {
            username = ((UserDetails)principal).getUsername();
        } else {
            //username = "anonymousUser"
            username = principal.toString();
        }

        //return current user username
        return username;
    }

    public User getCurrentUser() {
        String username = getCurrentUserUsername();
        return findByUsername(username);
    }


    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        //find user by username in database
        User user = repo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
