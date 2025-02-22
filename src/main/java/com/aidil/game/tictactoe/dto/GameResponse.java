package com.aidil.game.tictactoe.dto;

import lombok.Data;
import lombok.Builder;
import com.aidil.game.tictactoe.model.GameState;
import com.aidil.game.tictactoe.model.Player;

@Data
@Builder
public class GameResponse {
    private Player[][] board;
    private int size;
    private Player currentPlayer;
    private GameState gameState;
    private String message;
}