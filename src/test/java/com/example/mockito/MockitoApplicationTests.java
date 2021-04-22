package com.example.mockito;

import com.example.mockito.model.Response;
import com.example.mockito.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MockitoApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void addUserTest() throws Exception {
        User user=new User();
        user.setName("xadmin");
        user.setRole("Admin");

        String jsonRequest = objectMapper.writeValueAsString(user);

        MvcResult result= mockMvc.perform(post("/user/addUser").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        Response response = objectMapper.readValue(resultContext, Response.class);

        assertTrue(response.isStatus()==Boolean.TRUE);
    }

    @Test
    void getUserTest() throws Exception {

        MvcResult result= mockMvc.perform(get("/user/getUsers").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContext = result.getResponse().getContentAsString();

        Response response = objectMapper.readValue(resultContext, Response.class);

        assertTrue(response.isStatus()==Boolean.TRUE);
    }

}
