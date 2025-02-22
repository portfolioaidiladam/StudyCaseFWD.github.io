package com.aidil.game.tictactoe.model;

import lombok.Getter;

@Getter
public enum Player {
    X("X"),
    O("O"),
    EMPTY("");

    private final String symbol;

    Player(String symbol) {
        this.symbol = symbol;
    }
}
