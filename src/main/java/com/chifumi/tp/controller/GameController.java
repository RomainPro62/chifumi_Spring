package com.chifumi.tp.controller;

import com.chifumi.tp.service.GameService;
import com.chifumi.tp.service.UserService;
import com.chifumi.tp.service.entity.GameRoundResult;
import com.chifumi.tp.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {

    private final GameService gameService;
    private final UserService userService;

    @Autowired
    public GameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping("/play")
    public String showGamePage(@SessionAttribute(name = "loggedInUserId", required = false) Long loggedInUserId, Model model) {
        if (loggedInUserId == null) {
            return "redirect:/login"; // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
        }
        // Utiliser l'identifiant de l'utilisateur pour obtenir ses informations
        User user = userService.getUserById(loggedInUserId);
        // Ajouter les informations de l'utilisateur au modèle
        model.addAttribute("user", user);
        return "game";
    }

    @PostMapping("/play")
    public String playRound(@RequestParam String userChoice, Model model) {
        GameRoundResult result = gameService.playRound(userChoice);

        model.addAttribute("userScore", result.getUserScore());
        model.addAttribute("computerScore", result.getComputerScore());
        model.addAttribute("roundsPlayed", result.getRoundsPlayed());
        model.addAttribute("gameResult", result.getResult());

        String imageSource = null;

        // Déterminer le chemin de l'image en fonction du résultat du jeu
        if (result.getUserScore() == 3) {
            imageSource = "images/win.png";
        } else if (result.getComputerScore() == 3) {
            imageSource = "images/lose.png";
        }

        // Ajouter le chemin de l'image au modèle
        model.addAttribute("imageSource", imageSource);

        return "game";
    }

    @GetMapping("/game-results")
    public String showGameResultsPage(@SessionAttribute(name = "loggedInUserId", required = false) Long loggedInUserId, Model model) {
        if (loggedInUserId == null) {
            return "redirect:/login"; // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
        }
        // Récupérer les résultats du jeu
        List<GameRoundResult> gameResults = gameService.getGameResults();
        model.addAttribute("gameResults", gameResults);
        return "game-results";
    }
}
