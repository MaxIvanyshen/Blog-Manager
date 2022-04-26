package ua.ivanyshen.blogmanagerapi.model.User;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.boot.model.source.internal.hbm.HibernateTypeSourceImpl;
import org.hibernate.boot.model.source.spi.HibernateTypeSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.ivanyshen.blogmanagerapi.model.Blog.Blog;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class User implements UserDetails {

    @Id
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Type(type = "list-array")
    @Column(name = "writing", columnDefinition = "VARCHAR(7)[]", nullable = true, unique = false)
    private List<String> writingBlogsList;

    @Type(type = "list-array")
    @Column(name = "reading", columnDefinition = "VARCHAR(7)[]", nullable = true, unique = false)
    private List<String> readingBlogsList;
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

    public void addWritingBlogId(String id) {
        if(writingBlogsList == null) {
            writingBlogsList = new ArrayList<>();
        }
        writingBlogsList.add(id);
    }

    public void addReadingBlogId(String id) {
        if(readingBlogsList == null) {
            readingBlogsList = new ArrayList<>();
        }
        readingBlogsList.add(id);
    }
}
