package com.example.user.phm.security;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.user.phm.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;
    private Environment env;

    @Autowired
    public WebSecurity(UserService userService, BCryptPasswordEncoder passwordEncoder, Environment env) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // http.authorizeRequests().antMatchers("/user/**", "users/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/**")
                .access("hasIpAddress('127.0.0.1')")
                .and()
                .addFilter(getAuthenticationFilter());
        // 이 uri로 호출되는 모든 것들을 통과시켜주겠다.
//        http.headers().frameOptions().disable();
        // h2-console 접근 안됨
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, env, authenticationManager());

        return authenticationFilter;
    }

    // select pwd from users where email =?
    // db_pwd(encrypted) == input.pwd(encrypted)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
