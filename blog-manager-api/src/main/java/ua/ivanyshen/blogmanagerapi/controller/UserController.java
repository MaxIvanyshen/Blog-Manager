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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User foundUser = userService.findById(id);

        UserResponse res = new UserResponse(foundUser);
        
        return ResponseEntity.ok(res);
    }

    @PostMapping("/users")
    public UserResponse createNewUser(@RequestBody UserRequest req) {
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        User requestCreatedUser = new User(req.getUsername(), req.getEmail(), encoder.encode(req.getPassword()));
        
        return new UserResponse(userService.save(requestCreatedUser));
    }

    @PostMapping("/login")
    //TODO: redo this thing
    public org.springframework.security.core.userdetails.User login(@RequestBody UserRequest req) {
        User foundUser = userService.findByUsername(req.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(encoder.matches(req.getPassword(), foundUser.getPassword())) {
            return userService.loadUserByUsername(req.getUsername());
        }

        return null;
    }

    @GetMapping("/users/current")
    public ResponseEntity<String> getCurrentUserUsername() {
        String currentUserUsername = userService.getCurrentUserUsername();
        return ResponseEntity.ok(currentUserUsername);
    }

}
