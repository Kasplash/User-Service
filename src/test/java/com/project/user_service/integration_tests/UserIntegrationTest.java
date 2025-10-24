package com.project.user_service.integration_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional  //issue a rollback after each test
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void invalid_auth_returns_401() throws Exception {
        String user = createUser(200902263854L);
        mockMvc.perform(post("/user")
                        .with(httpBasic("admin", "invalid-test-password"))
                        .contentType("application/json")
                        .content(user))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void user_can_be_created() throws Exception {
        String user = createUser(200902263854L);

        mockMvc.perform(post("/user")
                        .with(httpBasic("admin", "test-password"))
                        .contentType("application/json")
                        .content(user))
                .andExpect(status().isOk())
                .andExpect(content().string(user));
    }

    @Test
    public void user_already_created_returns_exception() throws Exception {
        String user = createUser(200902263854L);
        mockMvc.perform(post("/user")
                .with(httpBasic("admin", "test-password"))
                .contentType("application/json")
                .content(user));

        mockMvc.perform(post("/user")
                        .with(httpBasic("admin", "test-password"))
                        .contentType("application/json")
                        .content(user))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User already exists"));
    }

    @Test
    public void user_can_be_fetched() throws Exception {
        String user = createUser(200902263854L);
        mockMvc.perform(post("/user")
                .with(httpBasic("admin", "test-password"))
                .contentType("application/json")
                .content(user));

        mockMvc.perform(get("/user")
                .with(httpBasic("admin", "test-password"))
                .param("ssn", "200902263854"))
                .andExpect(status().isOk())
                .andExpect(content().string(user));
    }

    @Test
    public void non_existing_user_cant_be_fetched_and_gives_exception() throws Exception {
        String user = createUser(200902263854L);
        mockMvc.perform(get("/user")
                        .with(httpBasic("admin", "test-password"))
                        .param("ssn", "200902263854"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No user found"));
    }

    @Test
    public void created_user_can_be_deleted_with_200ok() throws Exception {
        String user = createUser(200902263854L);
        mockMvc.perform(post("/user")
                .with(httpBasic("admin", "test-password"))
                .contentType("application/json")
                .content(user));

        mockMvc.perform(delete("/user")
                        .with(httpBasic("admin", "test-password"))
                        .param("ssn", "200902263854"))
                .andExpect(status().isOk());
    }

    @Test
    public void non_existing_user_cant_be_deleted() throws Exception {
        mockMvc.perform(delete("/user")
                        .with(httpBasic("admin", "test-password"))
                        .param("ssn", "200902263854"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No user to delete"));
    }

    @Test
    public void existing_user_can_be_updated() throws Exception {
        String user = createUser(200902263854L);

        mockMvc.perform(post("/user")
                .with(httpBasic("admin", "test-password"))
                .contentType("application/json")
                .content(user));

        mockMvc.perform(patch("/user")
                        .with(httpBasic("admin", "test-password"))
                        .contentType("application/json")
                        .content(user))
                .andExpect(status().isCreated())
                .andExpect(content().string("Updated entries: 1"));
    }

    @Test
    public void non_existing_user_is_not_updated() throws Exception {
        String user = createUser(200902263854L);
        String unexpectedUser = createUser(100902263854L);

        mockMvc.perform(post("/user")
                .with(httpBasic("admin", "test-password"))
                .contentType("application/json")
                .content(user));

        mockMvc.perform(patch("/user")
                        .with(httpBasic("admin", "test-password"))
                        .contentType("application/json")
                        .content(unexpectedUser))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No entries to update"));

    }

    private String createUser(long ssn) {
        return "{\"ssn\":" + ssn + ",\"name\":\"Larry\",\"address\":\"10 Downing St\",\"age\":15}";
    }
}
