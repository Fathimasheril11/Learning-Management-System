package com.example.Learning_App.controller;

import com.example.Learning_App.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(DashboardController.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /dashboard without session -> redirect to /")
    void dashboard_noSession_redirects() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("GET /dashboard with session -> shows dashboard with courses attribute")
    void dashboard_withSession_showsDashboard() throws Exception {
        User user = new User("kevin","k@example.com","pw");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/dashboard").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("courses"));
    }

    @Test
    @DisplayName("GET /dashboard/course/JS returns course view with attributes")
    void course_page_showsCourse() throws Exception {
        User user = new User("kevin","k@example.com","pw");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/dashboard/course/JS").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("course"))
                .andExpect(model().attributeExists("courseName"))
                .andExpect(model().attributeExists("basics"))
                .andExpect(model().attributeExists("functions"))
                .andExpect(model().attributeExists("examples"));
    }
}
