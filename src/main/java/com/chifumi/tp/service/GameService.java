package com.chifumi.tp.service;

import com.chifumi.tp.service.entity.Game;
import com.chifumi.tp.service.entity.GameRoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final Game game;

    @Autowired
    public GameService(@Value("${game.roundsToWin}") int roundsToWin) {
        this.game = new Game(roundsToWin);
    }

    public GameRoundResult playRound(String userChoice) {
        return game.playRound(userChoice);
    }
}
