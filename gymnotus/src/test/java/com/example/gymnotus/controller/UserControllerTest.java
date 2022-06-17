package com.example.gymnotus.controller;

import com.example.gymnotus.enums.GenderType;
import com.example.gymnotus.model.User;
import com.example.gymnotus.repository.UserRepository;
import com.example.gymnotus.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class UserControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getUser_whenCorrectRequest_thenValuesMatched() throws Exception {
        User newUser = new User();
        newUser.setUsername("testers123");
        newUser.setGenderType(GenderType.MALE);
        newUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        newUser.setHeight(180.0);
        newUser.setWeight(100.0);
        userRepository.save(newUser);

        MvcResult mvcResult = mockMvc.perform(get("/users/{id}", newUser.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        User user  = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(newUser.getId());
        assertThat(user.getUsername()).isEqualTo("testers123");
        assertThat(user.getGenderType()).isEqualTo(GenderType.MALE);
        assertThat(user.getBirthDate()).isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        assertThat(user.getHeight()).isEqualTo(180.0);
        assertThat(user.getWeight()).isEqualTo(100.0);
    }



    @Test
    void getUser_whenPathVariableTypeIsNoteCorrect_then400() throws Exception {
        mockMvc.perform(get("/users/anyString")).andExpect(status().isBadRequest());
    }

    @Test
    void getUser_whenIdNotExistsInDb_then404() throws Exception {
        mockMvc.perform(get("/users/100000000")).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void addUser_whenCorrectRequest_then201() throws Exception {
        User newUser = new User();
        newUser.setUsername("testers12345");
        newUser.setGenderType(GenderType.FEMALE);
        newUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        newUser.setHeight(180.0);
        newUser.setWeight(100.0);

        MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        User user  = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("testers12345");
        assertThat(user.getGenderType()).isEqualTo(GenderType.FEMALE);
        assertThat(user.getBirthDate()).isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        assertThat(user.getHeight()).isEqualTo(180.0);
        assertThat(user.getWeight()).isEqualTo(100.0);
    }

    @Test
    void addUser_whenRequestWithoutUsername_then400() throws Exception {
        User newUser = new User();
        newUser.setGenderType(GenderType.FEMALE);
        newUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        newUser.setHeight(180.0);
        newUser.setWeight(100.0);

        MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    void addUser_whenRequestWithWrongUsernameLength_then400() throws Exception {
        User newUser = new User();
        newUser.setUsername("test");
        newUser.setGenderType(GenderType.FEMALE);
        newUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        newUser.setHeight(180.0);
        newUser.setWeight(100.0);

        MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    void addUser_whenRequestWithExistsUsername_then400() throws Exception {
        User newUser = new User();
        newUser.setUsername("tester123");
        newUser.setGenderType(GenderType.FEMALE);
        newUser.setHeight(180.0);
        newUser.setWeight(100.0);

        MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    void addUser_whenWrongInputsTypeInRequest_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("username", "test123ers");
        body.put("genderType", "Female");
        body.put("height","170");
        body.put("weight","110");

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    void updateUser_whenCorrectRequest_then200() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("test123");
        updatedUser.setGenderType(GenderType.MALE);
        updatedUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        updatedUser.setWeight(70.0);
        updatedUser.setHeight(180.0);


        MvcResult mvcResult = mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        User user  = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("test123");
        assertThat(user.getGenderType()).isEqualTo(GenderType.MALE);
        assertThat(user.getBirthDate()).isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        assertThat(user.getWeight()).isEqualTo(70.0);
        assertThat(user.getHeight()).isEqualTo(180.0);
    }

    @Test
    @Transactional
    void updateUser_whenTryUpdateNotExistsUser_then404() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("id", "1000000");
        body.put("username", "test123");
        body.put("genderType", "FEMALE");
        body.put("height","170.0");
        body.put("weight","110.0");

        mockMvc.perform(put("/users/1000000").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateUser_whenWrongInputsTypeInRequest_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("id", "1");
        body.put("username", "test123");
        body.put("genderType", "Female");
        body.put("height","170");
        body.put("weight","110");

        mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    void deleteUser_whenCorrectRequest_then200() throws Exception {
        User newUser = new User();
        newUser.setUsername("testers123");
        newUser.setGenderType(GenderType.MALE);
        newUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        newUser.setHeight(180.0);
        newUser.setWeight(100.0);
        userRepository.save(newUser);

        MvcResult mvcResult = mockMvc.perform(delete("/users/{id}", newUser.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}