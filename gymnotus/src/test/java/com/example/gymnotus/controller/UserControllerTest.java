package com.example.gymnotus.controller;

import com.example.gymnotus.enums.GenderType;
import com.example.gymnotus.model.User;
import com.example.gymnotus.repository.UserRepository;
import com.example.gymnotus.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUser_whenCorrectRequest_thenValuesMatched() throws Exception {
        long id = 1L;
        User user = new User();
        user.setUsername("test");
        user.setGenderType(GenderType.MALE);
        user.setHeight(180.0);
        user.setWeight(100.0);
        when(userService.getUser(id)).thenReturn(user);
        mockMvc.perform(get("/users/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.genderType").value(user.getGenderType().toString()))
                .andExpect(jsonPath("$.height").value(user.getHeight()))
                .andExpect(jsonPath("$.weight").value(user.getWeight()))
                .andDo(print());
    }

    @Test
    void getUser_whenPathVariableTypeIsNoteCorrect_then400() throws Exception {
        mockMvc.perform(get("/users/anyString")).andExpect(status().is4xxClientError());
    }

    @Test
    void addUser_whenCorrectRequest_then200() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("username","user23");
        body.put("genderType", "MALE");
        body.put("birthDate","2022-05-17");
        body.put("height","180");
        body.put("weight","100");
        body.put("created","2022-05-17T11:10:09.986Z");

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    void addUser_whenRequestWithoutUsername_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void addUser_whenRequestWithWrongUsernameLength_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("username", "a");

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void updateUser_whenCorrectRequest_then200() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("id", "1");
        body.put("username", "test123");
        body.put("genderType", "FEMALE");
        body.put("height","170.0");
        body.put("weight","110.0");

        mockMvc.perform(put("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateUser_whenWrongInputInRequest_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("id", "1");
        body.put("username", "test123");
        body.put("genderType", "Female");
        body.put("height","170");
        body.put("weight","110");

        mockMvc.perform(put("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deleteUser_whenCorrectRequest_then204() throws Exception {
        long id = 1L;
        doNothing().when(userService).deleteUser(id);
        mockMvc.perform(delete("/users/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteUser_whenPathVariableTypeIsNoteCorrect_then400() throws Exception {
        mockMvc.perform(delete("/users/anyString")).andExpect(status().is4xxClientError());
    }

}