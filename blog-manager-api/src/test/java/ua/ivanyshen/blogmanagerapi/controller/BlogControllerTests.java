package ua.ivanyshen.blogmanagerapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;
import ua.ivanyshen.blogmanagerapi.model.Blog.BlogRepository;
import ua.ivanyshen.blogmanagerapi.model.Blog.CreateBlogRequest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BlogControllerTests {

    private BlogRepository blogRepo;
    private BlogController controller;

    @Autowired
    public BlogControllerTests(BlogRepository blogRepo, BlogController controller) {
        this.blogRepo = blogRepo;
        this.controller = new BlogController(blogRepo);
    }

    @Test
    void getBlogTest() {
        String id = "8jd6qxl";

        assertThat(controller.getBlog(id).getBody().getName())
                .isEqualTo("Hello World");

    }

    @Test
    void createBlogTest() {
        CreateBlogRequest req = new CreateBlogRequest("Test Blog", "Test", "A test for creating blogs");

        assertThat(controller.createBlog(req).getName())
                .isEqualTo(req.getName());
    }
}
