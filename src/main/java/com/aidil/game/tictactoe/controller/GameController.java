package com.aidil.game.tictactoe.controller;

import com.aidil.game.tictactoe.dto.GameResponse;
import com.aidil.game.tictactoe.model.GameBoard;
import com.aidil.game.tictactoe.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<GameResponse> startGame(@RequestParam int size) {
        try {
            gameService.initializeGame(size);
            GameBoard board = gameService.getGameBoard();

            return ResponseEntity.ok(GameResponse.builder()
                    .board(board.getBoard())
                    .size(board.getSize())
                    .currentPlayer(board.getCurrentPlayer())
                    .gameState(board.getGameState())
                    .message("Game started successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(GameResponse.builder()
                            .message("Failed to start game: " + e.getMessage())
                            .build());
        }
    }

    @PostMapping("/move")
    public ResponseEntity<GameResponse> makeMove(@RequestParam int row, @RequestParam int col) {
        try {
            log.info("Making move at row: {}, col: {}", row, col);
            if (gameService.makeMove(row, col)) {
                GameBoard board = gameService.getGameBoard();
                return ResponseEntity.ok(GameResponse.builder()
                        .board(board.getBoard())
                        .size(board.getSize())
                        .currentPlayer(board.getCurrentPlayer())
                        .gameState(board.getGameState())
                        .message("Move successful")
                        .build());
            }
            return ResponseEntity.badRequest()
                    .body(GameResponse.builder()
                            .message("Invalid move")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(GameResponse.builder()
                            .message("Error making move: " + e.getMessage())
                            .build());
        }
    }

    @GetMapping("/state")
    public ResponseEntity<GameResponse> getGameState() {
        try {
            GameBoard board = gameService.getGameBoard();
            return ResponseEntity.ok(GameResponse.builder()
                    .board(board.getBoard())
                    .size(board.getSize())
                    .currentPlayer(board.getCurrentPlayer())
                    .gameState(board.getGameState())
                    .message("Game state retrieved successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(GameResponse.builder()
                            .message("Error getting game state: " + e.getMessage())
                            .build());
        }
    }
}
