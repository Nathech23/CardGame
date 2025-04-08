package com.model;

public enum Suit {
    NONO (0), DIAMONDS(1), HEARTS(2), SPADES(3), CLUBS(4);

    int suit;

    private Suit(int value) {
        suit = value;
    }

    public int value() {
        return suit;
    }
}