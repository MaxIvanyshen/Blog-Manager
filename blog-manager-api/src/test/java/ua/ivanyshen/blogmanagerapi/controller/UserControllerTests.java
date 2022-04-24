package ua.ivanyshen.blogmanagerapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import ua.ivanyshen.blogmanagerapi.model.User.CreateUserRequest;
import ua.ivanyshen.blogmanagerapi.model.User.User;
import ua.ivanyshen.blogmanagerapi.model.User.UserRepository;
import ua.ivanyshen.blogmanagerapi.model.User.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserRepository repo;

    @Test
    void createNewUserTest() {
        CreateUserRequest req = new CreateUserRequest("Frozr", "maxivanyshen@gmail.com", "nastya2016");

        UserController controller = new UserController(repo);

        assertThat(controller.createNewUser(req)
                .getUsername())
                .isEqualTo(req.getUsername());
    }

    @Test
    void getUserTest() {
        String id = "6cV3ztBm9GMYKGO";

        UserResponse res = new UserResponse("6cV3ztBm9GMYKGO", "Frozr", "maxivanyshen@gmail.com");

        ResponseEntity<UserResponse> resEntity = ResponseEntity.ok(res);

        UserController controller = new UserController(repo);

        assertThat(controller.getUser(id).getBody().getId())
                .isEqualTo(resEntity.getBody().getId());

    }
}
