package com.games;

import com.model.Deck;
import com.model.NormalDeck;
import com.model.SmallDeck;
import com.model.TestDeck;

public class DeckFactory {
    public enum DeckType {
        Normal,
        Small,
        Test
    };
    public static Deck makeDeck(DeckType type) {
        switch (type) {
            case Normal: return new NormalDeck();
            case Small: return new SmallDeck();
            case Test: return new TestDeck();
        }

        return new NormalDeck();
    }
}
