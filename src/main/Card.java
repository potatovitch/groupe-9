package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Card {
    CardColor color;
    int value;

    public Card(CardColor color, int value) {
        this.color = color;
        this.value = value;
    }

    public CardColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public static ArrayList<Card> createDeck() { // deck trié
        ArrayList<Card> deck = new ArrayList<Card>(52);
        for (CardColor color : CardColor.values()) {
            for (int value = 1; value < 14; value++) {
                deck.add(new Card(color, value));
            }
        }
        return deck;
    }

    public static HashMap<CardColor, ArrayList<Integer>> createEmptyDefausse() {
        return new HashMap<CardColor, ArrayList<Integer>>() {{
            put(CardColor.HEARTS, new ArrayList<Integer>());
            put(CardColor.DIAMONDS, new ArrayList<Integer>());
            put(CardColor.CLUBS, new ArrayList<Integer>());
            put(CardColor.SPADES, new ArrayList<Integer>());
        }};
    }

    public static void mixeDeck(ArrayList<Card> deck) {
        ArrayList<Card> newDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int index = (int) (Math.random() * deck.size());
            newDeck.add(deck.get(index));
            deck.remove(index);
        }
        deck.addAll(newDeck);
    }

    @Override
    public String toString() {
        if (value == 1) {
            return color.toString().charAt(0) + "A";
        } else if (value == 11) {
            return color.toString().charAt(0) + "J";
        } else if (value == 12) {
            return color.toString().charAt(0) + "Q";
        } else if (value == 13) {
            return color.toString().charAt(0) + "K";
        }
        return color.toString().charAt(0) + valueToString();
    }

    public String valueToString() {
        return Integer.toString(value);
    }
}
