package ua.ivanyshen.blogmanagerapi.model.Blog;

public class CreateBlogRequest {
    private String name;
    private String topic;
    private String description;

    public CreateBlogRequest(String name, String topic, String description) {
        this.name = name;
        this.topic = topic;
        this.description = description;
    }

    public CreateBlogRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
