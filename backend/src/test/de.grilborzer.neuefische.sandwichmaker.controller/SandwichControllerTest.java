package de.grilborzer.neuefische.sandwichmaker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SandwichControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllSandwiches() throws Exception {
        mockMvc.perform(get("/api/sandwich"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }

    @Test
    void saveSandwich() {
    }

    @Test
    void getSandwichByID() {
    }

    @Test
    void updateTodo() {
    }

    @Test
    void deleteTodoById() {
    }
}