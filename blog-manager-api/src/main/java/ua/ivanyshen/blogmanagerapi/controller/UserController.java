package ua.ivanyshen.blogmanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;
import ua.ivanyshen.blogmanagerapi.model.User.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User u = service.findById(id);

        UserResponse res = new UserResponse();
        res.setId(u.getId());
        res.setUsername(u.getUsername());
        res.setEmail(u.getEmail());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/users")
    public UserResponse createNewUser(@RequestBody UserRequest req) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User u = new User(req.getUsername(), req.getEmail(), encoder.encode(req.getPassword()));
        return new UserResponse(service.save(u));
    }

    @PostMapping("/login")
    //TODO: redo this thing
    public org.springframework.security.core.userdetails.User login(@RequestBody UserRequest req) {
        User u = service.findByUsername(req.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(encoder.matches(req.getPassword(), u.getPassword())) {
            return service.loadUserByUsername(req.getUsername());
        }

        return null;
    }

    @GetMapping("/users/current")
    public ResponseEntity<String> currentUserUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return ResponseEntity.ok(username);
    }

}
