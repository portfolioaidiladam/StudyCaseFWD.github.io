package com.aidil.game.tictactoe.model;

import lombok.Getter;
import java.util.Arrays;

@Getter
public class GameBoard {
    private final int size;
    private final Player[][] board;
    private Player currentPlayer;
    private GameState gameState;

    public GameBoard(int size) {
        this.size = size;
        this.board = new Player[size][size];
        initializeBoard();
        this.currentPlayer = Player.X;
        this.gameState = GameState.IN_PROGRESS;
    }

    private void initializeBoard() {
        for (Player[] row : board) {
            Arrays.fill(row, Player.EMPTY);
        }
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            updateGameState(row, col);
            togglePlayer();
            return true;
        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < size &&
                col >= 0 && col < size &&
                board[row][col] == Player.EMPTY &&
                gameState == GameState.IN_PROGRESS;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    private void updateGameState(int lastRow, int lastCol) {
        if (checkWin(lastRow, lastCol)) {
            gameState = GameState.WIN;
            return;
        }
        if (isBoardFull()) {
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWin(int lastRow, int lastCol) {
        return checkRow(lastRow) ||
                checkColumn(lastCol) ||
                checkDiagonal() ||
                checkAntiDiagonal();
    }

    private boolean checkRow(int row) {
        for (int col = 0; col < size; col++) {
            if (board[row][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col) {
        for (int row = 0; row < size; row++) {
            if (board[row][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal() {
        for (int i = 0; i < size; i++) {
            if (board[i][i] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal() {
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoardFull() {
        for (Player[] row : board) {
            for (Player cell : row) {
                if (cell == Player.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
