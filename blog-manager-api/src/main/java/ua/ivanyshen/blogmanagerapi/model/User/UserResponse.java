package ua.ivanyshen.blogmanagerapi.model.User;

import java.util.List;

public class UserResponse {
    private String id;
    private String username;
    private String email;

    private List<String> writingBlogsList;

    private List<String> readingBlogsList;

    public UserResponse() {}

    public UserResponse(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserResponse(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.writingBlogsList = u.getWritingBlogsList();
        this.readingBlogsList = u.getReadingBlogsList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getWritingBlogsList() {
        return writingBlogsList;
    }

    public void setWritingBlogsList(List<String> writingBlogsList) {
        this.writingBlogsList = writingBlogsList;
    }

    public List<String> getReadingBlogsList() {
        return readingBlogsList;
    }

    public void setReadingBlogsList(List<String> readingBlogsList) {
        this.readingBlogsList = readingBlogsList;
    }
}
