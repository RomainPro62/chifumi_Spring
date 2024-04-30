package com.chifumi.tp.service.entity;

public class GameRoundResult {
    private final String userChoice;
    private final String computerChoice;
    private final String result;
    private final int userScore;
    private final int computerScore;
    private final int roundsPlayed;

    public GameRoundResult(String userChoice, String computerChoice, String result, int userScore, int computerScore, int roundsPlayed) {
        this.userChoice = userChoice;
        this.computerChoice = computerChoice;
        this.result = result;
        this.userScore = userScore;
        this.computerScore = computerScore;
        this.roundsPlayed = roundsPlayed;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public String getComputerChoice() {
        return computerChoice;
    }

    public String getResult() {
        return result;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }
}