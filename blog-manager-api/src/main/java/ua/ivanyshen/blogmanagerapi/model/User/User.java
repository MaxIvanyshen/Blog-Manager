package ua.ivanyshen.blogmanagerapi.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "writing", nullable = true, unique = false)
//    private String writing = "";
//
//    @Column(name = "reading", nullable = true, unique = false)
//    private String reading = "";
//
//    @Type(type="org.hibernate.type.BinaryType")
//    @Column(name = "icon", nullable = true, unique = false)
//    private byte[] icon;

    @Transient
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
        this.id = generateID();
    }

    public User(String username, String password) {
        this.id = generateID();
        this.username = username;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.id = generateID();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public byte[] getIcon() {
//        return icon;
//    }
//
//    public void setIcon(byte[] icon) {
//        this.icon = icon;
//    }
//
//    public void addWritingBlog(String blogName) {
//        if(writing.equals("")) {
//            writing = blogName;
//        }
//        else {
//            writing = writing + "//" + blogName;
//        }
//    }
//
//    public void addReadingBlog(String blogName) {
//        if(reading.equals("")) {
//            reading = blogName;
//        }
//        else {
//            reading = reading + "//" + blogName;
//        }
//    }
//
//    public ArrayList<String> getWritingBlogs() {
//        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(writing.split("//")));
//        return arrayList;
//    }
//
//    public ArrayList<String> getReadingBlogs() {
//        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(reading.split("//")));
//        return arrayList;
//    }

    public boolean equals(User u) {
        if(u.getEmail().equals(this.getEmail()) && u.getUsername().equals(this.getUsername()) && u.getId().equals(this.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("User: {id: %s; username: %s; email: %s; password: %s;}", id, username, email, password);
    }

    private String generateID() {
        int n = 15;
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
