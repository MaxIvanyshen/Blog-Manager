package ua.ivanyshen.blogmanagerapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogRepository;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogService;
import ua.ivanyshen.blogmanagerapi.model.Blog.CreateBlogRequest;
import ua.ivanyshen.blogmanagerapi.model.User.User;
import ua.ivanyshen.blogmanagerapi.model.User.UserService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BlogControllerTests {

    private BlogService blogService;

    private UserService userService;

    private BlogController controller;

    @Autowired
    public BlogControllerTests(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.controller = new BlogController(blogService, userService);
        this.userService = userService;
    }

    @Test
    void getBlogTest() {
        String id = "8jd6qxl";

        assertThat(controller.getBlog(id).getBody().getName())
                .isEqualTo("Hello World");

    }

    @Test
    void createBlogTest() {
        Blog b = new Blog("Test Blog", "Test", "A test for creating blogs");

        b = blogService.save(b);

        User u = userService.findById("Q3hsh81pb7EcGmi");

        u.addWritingBlogId(b.getId());

        userService.save(u);

        assertThat(u.getWritingBlogsList().get(u.getWritingBlogsList().size()-1))
                .isEqualTo(b.getId());
    }

    @Test
    void addBlogToFavoritesTest() {

        User u = userService.findById("MTNsTZX5iNc3609");

        Blog b = new Blog("Test Blog", "Test", "A test for liking blogs");

        b.setReadersCount(b.getReadersCount() + 1);
        blogService.save(b);

        u.addReadingBlogId(b.getId());

        userService.save(u);

        assertThat(u.getReadingBlogsList().get(u.getReadingBlogsList().size() - 1))
                .isEqualTo(b.getId());
    }

    @Test
    void deleteReadingBlogs() {
        User u = userService.findById("MTNsTZX5iNc3609");
        u.getReadingBlogsList().clear();
    }
}
