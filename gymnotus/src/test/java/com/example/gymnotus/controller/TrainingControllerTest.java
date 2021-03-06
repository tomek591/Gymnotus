package com.example.gymnotus.controller;

import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoPutRequest;
import com.example.gymnotus.enums.TrainingType;
import com.example.gymnotus.exception.training.UserWithThisIdNotExistsException;
import com.example.gymnotus.model.Training;
import com.example.gymnotus.repository.TrainingRepository;
import com.example.gymnotus.repository.UserRepository;
import com.example.gymnotus.service.TrainingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class TrainingControllerTest {

    @Autowired
    TrainingService trainingService;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void addTraining_whenCorrectRequest_then201() throws Exception {
        Training newTraining = new Training();
        newTraining.setUserId(10L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));


        MvcResult mvcResult = mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTraining)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        Training training = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Training.class);
        assertThat(training).isNotNull();
        assertThat(training.getUserId()).isEqualTo(10L);
        assertThat(training.getTrainingType()).isEqualTo(TrainingType.STRENGTH);
        assertThat(training.getName()).isEqualTo("FBW");
        assertThat(training.getDate()).isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
    }

    @Test
    @Transactional
    void addTraining_whenCorrectRequestWithEmptyNameAndDate_then201() throws Exception {
        Training newTraining = new Training();
        newTraining.setUserId(1L);
        newTraining.setTrainingType(TrainingType.STRENGTH);


        MvcResult mvcResult = mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTraining)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        Training training = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Training.class);
        assertThat(training).isNotNull();
        assertThat(training.getUserId()).isEqualTo(1L);
        assertThat(training.getTrainingType()).isEqualTo(TrainingType.STRENGTH);
    }

    @Test
    void addTraining_whenRequestWithNotExistUser_then400() throws Exception {
        Training newTraining = new Training();
        newTraining.setUserId(11L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));

        mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTraining)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserWithThisIdNotExistsException))
                .andDo(print())
                .andReturn();

    }

    @Test
    void addTraining_whenRequestWithoutUserId_then400() throws Exception {
        Training newTraining = new Training();
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        newTraining.setTrainingType(TrainingType.STRENGTH);

        mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTraining)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(print())
                .andReturn();
    }

    @Test
    void addTraining_whenRequestWithoutTrainingType_then400() throws Exception {
        Training newTraining = new Training();
        newTraining.setUserId(10L);
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));

        mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTraining)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(print())
                .andReturn();
    }


    @Test
    void addTraining_whenRequestWithWrongTypeOfUserId_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("userId", "string");
        body.put("name", "string");
        body.put("date","2017-09-09");
        body.put("trainingType","STRENGTH");

        mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andDo(print())
                .andReturn();
    }

    @Test
    void addTraining_whenRequestWithWrongTypeOfTrainingType_then400() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("userId", "10");
        body.put("name", "string");
        body.put("date","2017-09-09");
        body.put("trainingType","string");

        mockMvc.perform(post("/trainings").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andDo(print())
                .andReturn();
    }

    @Test
    @Transactional
    void updateTraining_whenCorrectRequest_then201() throws Exception {
        TrainingDtoPutRequest updatedTrainingBody = new TrainingDtoPutRequest(
                "FBW A",
                new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09")
        );

        MvcResult mvcResult = mockMvc.perform(put("/trainings/3").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTrainingBody)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        TrainingDtoPutRequest trainingDtoPutRequest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TrainingDtoPutRequest.class);
        assertThat(trainingDtoPutRequest).isNotNull();
        assertThat(trainingDtoPutRequest.name()).isEqualTo("FBW A");
        assertThat(trainingDtoPutRequest.date()).isEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
    }

    @Test
    @Transactional
    void updateTraining_whenRequestWithValue_thenOthersAreNotNull() throws Exception {
        Map<String,Object> updatedTrainingBody = new HashMap<>();
        updatedTrainingBody.put("name", "FBW B");

        MvcResult mvcResult = mockMvc.perform(put("/trainings/3").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTrainingBody)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        TrainingDtoPutRequest trainingDtoPutRequest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TrainingDtoPutRequest.class);
        assertThat(trainingDtoPutRequest).isNotNull();
        assertThat(trainingDtoPutRequest.name()).isEqualTo("FBW B");
        assertThat(trainingDtoPutRequest.date()).isNotNull();
    }

    @Test
    @Transactional
    void updateTraining_whenTryUpdateNotExistsTraining_then404() throws Exception {
        TrainingDtoPutRequest updatedTrainingBody = new TrainingDtoPutRequest(
                "FBW A",
                new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09")
        );

        mockMvc.perform(put("/trainings/1000000").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTrainingBody)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchElementException))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateTraining_whenWrongInputsTypeInRequest_then400() throws Exception {
        Map<String,Object> updatedTrainingBody = new HashMap<>();
        updatedTrainingBody.put("name", "FBW B");
        updatedTrainingBody.put("date", "string");

        mockMvc.perform(put("/trainings/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTrainingBody)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Transactional
    void deleteTraining_whenCorrectRequest_then204() throws Exception {
        Training newTraining = new Training();
        newTraining.setUserId(10L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        trainingRepository.save(newTraining);

        mockMvc.perform(delete("/trainings/{id}", newTraining.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteTraining_whenIdNotExistsInDb_then404() throws Exception {
        mockMvc.perform(delete("/trainings/100000000")).andExpect(status().isNotFound());
    }
}
