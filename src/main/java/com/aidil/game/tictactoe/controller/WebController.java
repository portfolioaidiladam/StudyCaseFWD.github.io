package com.aidil.game.tictactoe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping({"/", "/tictactoe"})
    public String home() {
        return "game";
    }
}