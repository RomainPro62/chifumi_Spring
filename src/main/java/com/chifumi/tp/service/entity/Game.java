package com.chifumi.tp.service.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    private final int roundsToWin;
    private int userScore;
    private int computerScore;
    private int roundsPlayed;
    private final Map<String, String> choices;

    public Game(int roundsToWin) {
        this.roundsToWin = roundsToWin;
        this.userScore = 0;
        this.computerScore = 0;
        this.roundsPlayed = 0;
        this.choices = new HashMap<>();
        choices.put("rock", "scissors");
        choices.put("paper", "rock");
        choices.put("scissors", "paper");
    }

    public GameRoundResult playRound(String userChoice) {
        String computerChoice = getRandomChoice();
        String result = calculateRoundResult(userChoice, computerChoice);
        updateScore(result);
        return new GameRoundResult(userChoice, computerChoice, result, userScore, computerScore, roundsPlayed);
    }

    private String getRandomChoice() {
        String[] options = {"rock", "paper", "scissors"};
        Random random = new Random();
        return options[random.nextInt(options.length)];
    }

    private String calculateRoundResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice))
            return "draw";
        else if (choices.get(userChoice).equals(computerChoice))
            return "win";
        else
            return "lose";
    }

    private void updateScore(String result) {
        switch (result) {
            case "win":
                userScore++;
                break;
            case "lose":
                computerScore++;
                break;
            default:
                break;
        }
        roundsPlayed++;
    }

    public Map<String, Integer> getScore() {
        Map<String, Integer> score = new HashMap<>();
        score.put("userScore", userScore);
        score.put("computerScore", computerScore);
        score.put("roundsPlayed", roundsPlayed);
        return score;
    }
}
