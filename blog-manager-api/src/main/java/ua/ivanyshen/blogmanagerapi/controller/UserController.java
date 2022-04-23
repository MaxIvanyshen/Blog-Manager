package ua.ivanyshen.blogmanagerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.blogmanagerapi.exception.ResourceNotFoundException;
import ua.ivanyshen.blogmanagerapi.model.User.CreateUserRequest;
import ua.ivanyshen.blogmanagerapi.model.User.User;
import ua.ivanyshen.blogmanagerapi.model.User.UserRepository;
import ua.ivanyshen.blogmanagerapi.model.User.UserResponse;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        User u = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserResponse res = new UserResponse();
        res.setId(u.getId());
        res.setUsername(u.getUsername());
        res.setEmail(u.getEmail());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/users")
    public UserResponse createNewUser(@RequestBody CreateUserRequest req) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User u = new User(req.getUsername(), req.getEmail(), encoder.encode(req.getPassword()));
        return new UserResponse(userRepo.save(u));
    }

}
