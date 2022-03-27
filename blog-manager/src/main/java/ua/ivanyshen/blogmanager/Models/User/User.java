package ua.ivanyshen.blogmanager.Models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    @Column(name = "writing", nullable = true, unique = false)
    private String writing = "";

    @Column(name = "reading", nullable = true, unique = false)
    private String reading = "";

    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "icon", nullable = true, unique = false)
    private byte[] icon;

    @Transient
    private String pass1;

    @Transient
    private String pass2;

    public User() {
        this.id = generateID();
    }

    public User(String username, String password) {
        this.id = generateID();
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
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

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public void addWritingBlog(String blogID) {
        if(writing.equals("")) {
            writing = blogID;
        }
        else {
            writing = writing + ", " + blogID;
        }
    }

    public void addReadingBlog(String blogID) {
        if(reading.equals("")) {
            reading = blogID;
        }
        else {
            reading = reading + ", " + blogID;
        }
    }

    public ArrayList<String> getWritingBlogs() {
        return new ArrayList<>(Arrays.asList(writing.split(", ")));
    }

    public ArrayList<String> getReadingBlogs() {
        return new ArrayList<>(Arrays.asList(reading.split(", ")));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Set<GrantedAuthority> authorities = new HashSet<>();
       authorities.add(new SimpleGrantedAuthority("USER"));
       return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
