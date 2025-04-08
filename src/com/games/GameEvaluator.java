package com.games;

import java.util.List;

import com.model.Player;

public interface GameEvaluator {
    public Player evaluateWinner(List<Player> players);
}