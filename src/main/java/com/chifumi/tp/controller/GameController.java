package com.chifumi.tp.controller;

import com.chifumi.tp.service.GameService;
import com.chifumi.tp.service.entity.GameRoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/play")
    public String playRound(@RequestParam String userChoice, Model model) {
        GameRoundResult result = gameService.playRound(userChoice);

        model.addAttribute("userScore", result.getUserScore());
        model.addAttribute("computerScore", result.getComputerScore());
        model.addAttribute("roundsPlayed", result.getRoundsPlayed());
        model.addAttribute("gameResult", result.getResult());

        return "game";
    }
    @GetMapping("/play")
    public String showGamePage() {
        return "game";
    }
}
