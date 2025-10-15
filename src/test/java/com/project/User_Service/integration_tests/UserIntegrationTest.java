package com.project.User_Service.integration_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional  //rollback after each test
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void user_created_returns_200ok() throws Exception {
        mockMvc.perform(post("/user")
                        .with(httpBasic("admin", "test-password"))
                        .contentType("application/json")
                        .content("{\"firstName\":\"Alice\",\"ssn\":1234}"))
                .andExpect(status().isOk());
    }
    @Test
    public void user_already_created_returns_400BadRequest() throws Exception {
        mockMvc.perform(post("/user")
                .with(httpBasic("admin", "test-password"))
                .contentType("application/json")
                .content("{\"firstName\":\"Alice\",\"ssn\":1234}"));

        mockMvc.perform(post("/user")
                        .with(httpBasic("admin", "test-password"))
                        .contentType("application/json")
                        .content("{\"firstName\":\"Alice\",\"ssn\":1234}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void created_user_can_be_deleted_with_200ok(){

    }
}
