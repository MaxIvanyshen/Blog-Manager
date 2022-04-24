package ua.ivanyshen.blogmanagerapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogRepository;
import ua.ivanyshen.blogmanagerapi.model.User.UserRequest;
import ua.ivanyshen.blogmanagerapi.model.User.UserRepository;
import ua.ivanyshen.blogmanagerapi.model.User.UserResponse;
import ua.ivanyshen.blogmanagerapi.model.User.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserControllerTests {

    private UserService service;
    private UserController controller;

    @Autowired
    public UserControllerTests(UserService service, BlogController controller) {
        this.service = service;
        this.controller = new UserController(service);
    }
    @Test
    void createNewUserTest() {
        UserRequest req = new UserRequest("Frozr", "maxivanyshen@gmail.com", "nastya2016");

        assertThat(controller.createNewUser(req)
                .getUsername())
                .isEqualTo(req.getUsername());
    }

    @Test
    void getUserTest() {
        String id = "6cV3ztBm9GMYKGO";

        UserResponse res = new UserResponse("6cV3ztBm9GMYKGO", "Frozr", "maxivanyshen@gmail.com");

        ResponseEntity<UserResponse> resEntity = ResponseEntity.ok(res);

        assertThat(controller.getUser(id).getBody().getId())
                .isEqualTo(resEntity.getBody().getId());

    }
}
