// src/test/java/com/game/tictactoe/controller/WebControllerTest.java
package com.aidil.game.tictactoe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void home_ShouldReturnGamePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"));
    }

    @Test
    void tictactoe_ShouldReturnGamePage() throws Exception {
        mockMvc.perform(get("/tictactoe"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"));
    }
}