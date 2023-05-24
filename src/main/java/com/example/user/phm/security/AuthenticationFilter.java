package com.example.user.phm.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.user.phm.service.UserService;
import com.example.user.phm.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserService userService;
    private Environment env;

    @Autowired
    public AuthenticationFilter(UserService userService,
                                Environment env,
                                AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.env = env;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, 
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(
                        new UsernamePasswordAuthenticationToken(
                                creds.getEmail(), 
                                creds.getPassword(), 
                                new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
       log.debug( ((User) authResult.getPrincipal()).getUsername());
    }

    
    
}
