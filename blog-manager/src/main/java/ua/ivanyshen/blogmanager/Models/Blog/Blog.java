package ua.ivanyshen.blogmanager.Models.Blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "topic", nullable = false, unique = false)
    private String topic;

    @Column(name = "description", nullable = true, unique = false)
    private String description;

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
