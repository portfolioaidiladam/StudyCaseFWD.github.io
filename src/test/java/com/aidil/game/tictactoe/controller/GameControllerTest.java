// src/test/java/com/game/tictactoe/controller/GameControllerTest.java
package com.aidil.game.tictactoe.controller;

import com.aidil.game.tictactoe.dto.GameResponse;
import com.aidil.game.tictactoe.model.GameBoard;
import com.aidil.game.tictactoe.model.GameState;
import com.aidil.game.tictactoe.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Mock
    private GameBoard gameBoard;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        when(gameService.getGameBoard()).thenReturn(gameBoard);
    }

    @Test
    void startGame_WithValidSize_ShouldReturnOkResponse() {
        // Given
        int size = 3;
        when(gameBoard.getSize()).thenReturn(size);
        when(gameBoard.getGameState()).thenReturn(GameState.IN_PROGRESS);

        // When
        ResponseEntity<GameResponse> response = gameController.startGame(size);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(gameService).initializeGame(size);
        assertNotNull(response.getBody());
        assertEquals(size, response.getBody().getSize());
    }

    @Test
    void makeMove_WithValidMove_ShouldReturnOkResponse() {
        // Given
        int row = 0;
        int col = 0;
        when(gameService.makeMove(row, col)).thenReturn(true);

        // When
        ResponseEntity<GameResponse> response = gameController.makeMove(row, col);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(gameService).makeMove(row, col);
        assertNotNull(response.getBody());
    }

    /*@Test
    void makeMove_WithInvalidMove_ShouldReturnBadRequest() {
        // Given
        int row = 0;
        int col = 0;
        when(gameService.makeMove(row, col)).thenReturn(false);

        // When
        ResponseEntity<GameResponse> response = gameController.makeMove(row, col);

        // Then
        assertTrue(response.getStatusCode().is4xxClientError());
        verify(gameService).makeMove(row, col);
    }*/
}