package main;

public enum CardColor {
    HEARTS("♥️"),
    DIAMONDS("♦️"),
    CLUBS("♣️"),
    SPADES("️️️️️️️♠️");

    private final String symbol;

    CardColor(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol; 
    }
}