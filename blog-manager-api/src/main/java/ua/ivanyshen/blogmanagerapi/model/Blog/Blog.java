package ua.ivanyshen.blogmanagerapi.model.Blog;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "topic", nullable = false, unique = false)
    private String topic;

    @Column(name = "description", nullable = true, unique = false)
    private String description;

    @Column(name = "readers", nullable = false, unique = false)
    private Long readersCount = Long.valueOf(0);

//    @Type(type="org.hibernate.type.BinaryType")
//    @Column(name = "icon", nullable = true, unique = false)
//    private byte[] icon;
//
//    @Type(type="org.hibernate.type.BinaryType")
//    @Column(name = "banner", nullable = true, unique = false)
//    private byte[] banner;

    public Blog() {
        this.id = generateID();
    }

    public Blog(String name, String topic, String description) {
        this.id = generateID();
        this.name = name;
        this.topic = topic;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return this.topic;
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

    public Long getReadersCount() {
        return readersCount;
    }

    public void setReadersCount(Long readersCount) {
        this.readersCount = readersCount;
    }

    //    public byte[] getIcon() {
//        return icon;
//    }
//
//    public void setIcon(byte[] icon) {
//        this.icon = icon;
//    }
//
//    public byte[] getBanner() {
//        return banner;
//    }
//
//    public void setBanner(byte[] banner) {
//        this.banner = banner;
//    }

    private String generateID() {
        int n = 7;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}

