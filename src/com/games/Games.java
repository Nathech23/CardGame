package com.games;

import com.controller.GameController;
import com.model.Deck;
import com.view.GameSwingView;

public class Games {

    public static void main(String args[]) {

        GameSwingView gsv = new GameSwingView();
        gsv.createAndShowGUI();

        GameController gc = new GameController(new Deck(), gsv, new HighCardGameEvaluator());
        //GameController gc = new GameController(new Deck(), new View(), new LowCardGameEvaluator());
        gc.run();
    }
}