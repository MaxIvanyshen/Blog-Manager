package ua.ivanyshen.blogmanagerapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.ivanyshen.blogmanagerapi.model.User.CreateUserRequest;
import ua.ivanyshen.blogmanagerapi.model.User.User;
import ua.ivanyshen.blogmanagerapi.model.User.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserRepository repo;

    @Test
    void createNewUserTest() {
        CreateUserRequest req = new CreateUserRequest("Frozr", "maxivanyshen@gmail.com", "nastya2016");

        UserController c = new UserController(repo);

        assertThat(c.createNewUser(req)
                .getUsername())
                .isEqualTo(req.getUsername());
    }
}
