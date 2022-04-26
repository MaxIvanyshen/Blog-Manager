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

    //get user rest api
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User foundUser = userService.findById(id);

        //create user response with data from found user
        UserResponse res = new UserResponse(foundUser);

        //return user response
        return ResponseEntity.ok(res);
    }

    //create user rest api
    @PostMapping("/users")
    public UserResponse createNewUser(@RequestBody UserRequest req) {
        //password encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        //create user from the request
        User requestCreatedUser = new User(req.getUsername(), req.getEmail(), encoder.encode(req.getPassword()));
        
        //return created user as user response
        return new UserResponse(userService.save(requestCreatedUser));
    }

    @PostMapping("/login")
    //TODO: redo this thing
    public org.springframework.security.core.userdetails.User login(@RequestBody UserRequest req) {
        User foundUser = userService.findByUsername(req.getUsername());

        //password encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(encoder.matches(req.getPassword(), foundUser.getPassword())) { //if passwords match
            return userService.loadUserByUsername(req.getUsername()); //log user in
        }
        //else 
        return null;
    }

    //get current logged user username
    @GetMapping("/users/current")
    public ResponseEntity<String> getCurrentUserUsername() {
        String currentUserUsername = userService.getCurrentUserUsername();
        return ResponseEntity.ok(currentUserUsername);
    }

}
