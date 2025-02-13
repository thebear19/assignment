package com.kasikornline.assignment.app.common.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SecurityConfigurationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPasswordEncoderBean() {
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        assertNotNull(passwordEncoder);
    }

    @Test
    public void testAuthenticationManagerBean() {
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
        assertNotNull(authenticationManager);
    }

    @Test
    @WithMockUser
    public void testSecuredEndpoint() throws Exception {
        mockMvc.perform(post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"000018b0e1a211ef95a30242ac180002\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"User_000018b0e1a211ef95a30242ac180002\",\"password\":\"123456\"}"))
                .andExpect(status().isOk());
    }
}
