package ua.ivanyshen.blogmanagerapi.model.Blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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

    public void setTopic(String str) {
        int a = Integer.parseInt(str);
        switch(a) {
            case 1: this.topic="Programming";
            case 2: this.topic="Travelling";
            case 3: this.topic="Food";
            case 4: this.topic="Sport";
            case 5: this.topic="Science";
            case 6: this.topic="Design";
            case 7: this.topic="Trading";
            case 8: this.topic="Anime";
            case 9: this.topic="Films";
            case 10: this.topic="Series";
            case 11: this.topic="Music";
            case 12: this.topic="Gaming";
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}

