package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.games.GameEvaluator;
import com.model.Deck;
import com.model.IPlayer;
import com.model.Player;
import com.model.PlayingCard;
import com.model.WinningPlayer;
import com.view.GameViewable;

public class GameController {

    enum GameState {
        AddingPlayers, CardsDealt, WinnerRevealed;
    }

    Deck deck;
    List<IPlayer> players;
    IPlayer winner;
    GameViewable view;
    GameState gameState;
    GameEvaluator evaluator;

    public GameController(Deck deck, GameViewable view, GameEvaluator gameEvaluator) {
        super();
        this.deck = deck;
        this.view = view;
        this.players = new ArrayList<IPlayer>();
        this.gameState = GameState.AddingPlayers;
        this.evaluator = gameEvaluator;
        view.setController(this);
    }

    public void run() {
        while (gameState == GameState.AddingPlayers) {
            view.promptForPlayerName();
        }

        switch (gameState) {
            case CardsDealt:
                view.promptForFlip();
                break;
            case WinnerRevealed:
                view.promptForNewGame();
                break;
        }
    }

    public void addPlayer(String playerName) {
        if (gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            view.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame() {
        if (gameState != GameState.CardsDealt) {
            deck.shuffle();
            int playerIndex = 1;
            for (IPlayer player : players) {
                player.addCardToHand(deck.removeTopCard());
                view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
        this.run();
    }

    public void flipCards() {
        int playerIndex = 1;
        for (IPlayer player : players) {
            PlayingCard pc = player.getCard(0);
            pc.flip();
            view.showCardForPlayer(playerIndex++, player.getName(),
                    pc.getRank().toString(), pc.getSuit().toString());
        }

        evaluateWinner();
        displayWinner();
        rebuildDeck();
        gameState = GameState.WinnerRevealed;
        this.run();
    }

    void evaluateWinner() {
        winner = new WinningPlayer(evaluator.evaluateWinner(players));
    }

    void displayWinner() {
        view.showWinner(winner.getName());
    }

    void rebuildDeck() {
        for (IPlayer player : players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

    public void nextAction(String nextChoice) {
        if("+q".equals(nextChoice)) {
            exitGame();
        }
        else {
            startGame();
        }
    }

    void exitGame() {
        System.exit(0);
    }
}