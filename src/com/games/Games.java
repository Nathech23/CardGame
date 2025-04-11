package com.games;

import com.controller.GameController;
import com.view.GameSwingView;
import com.games.DeckFactory.DeckType;
import com.games.HighCardGameEvaluator;
import com.view.GameSwingPassiveView;
import com.view.GameViewables;


public class Games {

    public static void main(String args[]) {
        GameViewables views = new GameViewables();

        GameSwingView gsv = new GameSwingView();
        gsv.createAndShowGUI();
        views.addViewable(gsv);

        for(int i = 0; i< 3; i++) {
            GameSwingPassiveView passiveView = new GameSwingPassiveView();
            passiveView.createAndShowGUI();

            views.addViewable(passiveView);

            // sleep to move new Swing frame on window
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        GameController gc = new GameController(DeckFactory.makeDeck(DeckType.Normal), views, new HighCardGameEvaluator());

        gc.run();
    }
}