package com.games;

import java.util.List;

import com.model.IPlayer;

public interface GameEvaluator {
    public IPlayer evaluateWinner(List<IPlayer> players);
}