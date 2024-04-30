package com.chifumi.tp.service;

import com.chifumi.tp.service.entity.Game;
import com.chifumi.tp.service.entity.GameRoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final Game game;
    private final List<GameRoundResult> gameResults;

    @Autowired
    public GameService(@Value("${game.roundsToWin}") int roundsToWin) {
        this.game = new Game(roundsToWin);
        this.gameResults = new ArrayList<>();
    }

    public GameRoundResult playRound(String userChoice) {
        GameRoundResult result = game.playRound(userChoice);
        gameResults.add(result);
        return result;
    }

    public List<GameRoundResult> getGameResults() {
        return gameResults;
    }
}

