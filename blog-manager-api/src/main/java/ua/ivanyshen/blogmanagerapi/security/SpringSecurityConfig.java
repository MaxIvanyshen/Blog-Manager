package ua.ivanyshen.blogmanagerapi.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import ua.ivanyshen.blogmanagerapi.model.User.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Configuration
@EnableConfigurationProperties
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //disable csrf
                .authorizeRequests().antMatchers("/api/v1/blogs/**").authenticated() //if user tries to make erquest to "/api/v1/blogs" he needs to be logged in
                .and().logout().logoutUrl("/api/v1/logout")                          //change logout url to "/api/v1/logout"/ instead of "/logout"
                .and().httpBasic()                                                   //use Basic Authorization
                .and().sessionManagement().disable();                                //disable session management
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
         //now Spring Security will use UserService to find user and log him in
        builder.userDetailsService(userService);                                   
    } 
    
    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder delPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        delPasswordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
        return delPasswordEncoder;
    }
}

