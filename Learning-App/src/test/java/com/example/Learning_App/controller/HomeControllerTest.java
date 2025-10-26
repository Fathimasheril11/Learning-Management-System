package com.example.Learning_App.controller;

import com.example.Learning_App.entity.User;
import com.example.Learning_App.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @DisplayName("GET / should return login view with model attribute user")
    void getHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @DisplayName("POST /register - username exists -> show register with error")
    void register_usernameExists_showsError() throws Exception {
        when(userService.findByUsername(anyString())).thenReturn(new User("tom","tom@example.com","pw"));

        mockMvc.perform(post("/register")
                        .param("username", "tom")
                        .param("email", "tom@example.com")
                        .param("password", "pw"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    @DisplayName("POST /register - success -> redirect to /")
    void register_success_redirects() throws Exception {
        when(userService.findByUsername(anyString())).thenReturn(null);

        mockMvc.perform(post("/register")
                        .param("username", "newuser")
                        .param("email", "new@example.com")
                        .param("password", "pw"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("POST /login - valid credentials -> redirect to /dashboard and session set")
    void login_validCredentials_redirectsAndSession() throws Exception {
        User u = new User("sam", "sam@example.com", "mypw");
        when(userService.findByUsername("sam")).thenReturn(u);

        MockHttpSession session = (MockHttpSession) mockMvc.perform(post("/login")
                        .param("username", "sam")
                        .param("password", "mypw"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"))
                .andReturn()
                .getRequest()
                .getSession(false);

        // session should exist and contain user attribute
        assert session != null;
        Object sessionUser = session.getAttribute("user");
        assert sessionUser instanceof User;
    }

    @Test
    @DisplayName("POST /login - invalid credentials -> back to login with error")
    void login_invalidCredentials_showsError() throws Exception {
        when(userService.findByUsername("bad")).thenReturn(null);

        mockMvc.perform(post("/login")
                        .param("username","bad")
                        .param("password","no"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}
