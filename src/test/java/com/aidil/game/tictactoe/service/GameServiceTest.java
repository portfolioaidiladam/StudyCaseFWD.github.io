// src/test/java/com/game/tictactoe/service/GameServiceTest.java
package com.aidil.game.tictactoe.service;

import com.aidil.game.tictactoe.model.GameBoard;
import com.aidil.game.tictactoe.model.GameState;
import com.aidil.game.tictactoe.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService = GameService.getInstance();


    @BeforeEach
    void setUp() {
      //  gameService = new GameService();
    }

    @Test
    void initializeGame_ShouldCreateNewGameBoard() {
        // Given
        int size = 3;

        // When
        gameService.initializeGame(size);
        GameBoard board = gameService.getGameBoard();

        // Then
        assertNotNull(board);
        assertEquals(size, board.getSize());
        assertEquals(GameState.IN_PROGRESS, board.getGameState());
        assertEquals(Player.X, board.getCurrentPlayer());
    }

    @Test
    void makeMove_ShouldReturnTrueForValidMove() {
        // Given
        gameService.initializeGame(3);
        int row = 0;
        int col = 0;

        // When
        boolean result = gameService.makeMove(row, col);

        // Then
        assertTrue(result);
        assertEquals(Player.O, gameService.getGameBoard().getCurrentPlayer());
    }

    @Test
    void makeMove_ShouldReturnFalseForInvalidMove() {
        // Given
        gameService.initializeGame(3);
        int row = 0;
        int col = 0;

        // When
        gameService.makeMove(row, col); // First move
        boolean result = gameService.makeMove(row, col); // Same position

        // Then
        assertFalse(result);
    }
}