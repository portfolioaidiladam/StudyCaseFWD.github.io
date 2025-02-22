package com.aidil.game.tictactoe.service;

import com.aidil.game.tictactoe.model.GameBoard;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class GameService {
    private static final GameService instance = new GameService();
    private GameBoard gameBoard;

    private GameService() {
        // Private constructor
        initializeGame(3);
    }

    public static GameService getInstance() {
        return instance;
    }

    // synchronized untuk thread safety
    public synchronized void initializeGame(int size) {
        this.gameBoard = new GameBoard(size);
    }

    public synchronized boolean makeMove(int row, int col) {
        if (gameBoard == null) {
            return false;
        }
        return gameBoard.makeMove(row, col);
    }

    public synchronized GameBoard getGameBoard() {
        return gameBoard;
    }
}
