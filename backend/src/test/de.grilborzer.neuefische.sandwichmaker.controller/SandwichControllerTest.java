package de.grilborzer.neuefische.sandwichmaker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.grilborzer.neuefische.sandwichmaker.model.Sandwich;
import de.grilborzer.neuefische.sandwichmaker.repository.SandwichRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SandwichControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SandwichRepository sandwichRepository;

    final String sandwichUrl = "/api/sandwich/";

    final Sandwich testSandwichWithId = Sandwich.builder()
            .id("1")
            .name("McFalaffel")
            .patty("Falaffel")
            .numberOfPatties(2)
            .extraWishes("Extra Zwiebeln")
            .isGrilled(true)
            .build();

    final Sandwich testSandwichWithoutId = Sandwich.builder()
            .name("McFalaffel")
            .patty("Falaffel")
            .numberOfPatties(2)
            .extraWishes("Extra Zwiebeln")
            .isGrilled(true)
            .build();

    @BeforeEach
    void prepareDatabase() {
        sandwichRepository.save(testSandwichWithId);
    }

    @Test
    @DirtiesContext
    void getAllSandwiches_shouldReturnListWithSingleSandwich() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(sandwichUrl))
                .andExpect(status().isOk())
                .andReturn();

        List<Sandwich> responseSandwichList = Arrays.asList(objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Sandwich[].class));
        assertEquals(responseSandwichList.get(0), testSandwichWithId);
    }

    @Test
    @DirtiesContext
    void saveSandwich_shouldReturnSandwichWithValidId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post(sandwichUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSandwichWithoutId)))
                .andExpect(status().isOk())
                .andReturn();

        Sandwich responseSandwich = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Sandwich.class);
        assertNotNull(responseSandwich.getId());
    }

    @Test
    @DirtiesContext
    void getSandwichById_shouldReturnExpectedSandwich() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(sandwichUrl + testSandwichWithId.getId()))
                .andExpect(status().isOk())
                .andReturn();

        Sandwich responseSandwich = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Sandwich.class);
        assertEquals(responseSandwich, testSandwichWithId);
    }

    @Test
    @DirtiesContext
    void updateTodo() throws Exception {
        Sandwich modifiedSandwich = testSandwichWithId.withName("Neuer Sandwich Name");

        MvcResult mvcResult = mockMvc.perform(put(sandwichUrl + modifiedSandwich.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifiedSandwich)))
                .andExpect(status().isOk())
                .andReturn();

        Sandwich responseSandwich = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Sandwich.class);
        assertEquals(responseSandwich, modifiedSandwich);
    }

    @Test
    @DirtiesContext
    void deleteTodoById_shouldReturnSentIdAndLeadToEmptyDatabase() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete(sandwichUrl + testSandwichWithId.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String responseSandwichId = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), String.class);
        assertEquals(responseSandwichId, testSandwichWithId.getId());
        assertTrue(sandwichRepository.findAll().isEmpty());
    }
}