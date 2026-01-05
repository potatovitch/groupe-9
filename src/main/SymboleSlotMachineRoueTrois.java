package main;

public enum SymboleSlotMachineRoueTrois {
    CHERRY1("cerise", 2, 4, 1),
    CHERRY2("cerise", 2, 4, 1),
    CHERRY3("cerise", 2, 4, 1),
    CHERRY4("cerise", 2, 4, 1),
    CHERRY5("cerise", 2, 4, 1),
    CHERRY6("cerise", 2, 4, 1),

    LEMON1("citron", 2, 10, 2),
    LEMON2("citron", 2, 10, 2),
    LEMON3("citron", 2, 10, 2),
    LEMON4("citron", 2, 10, 2),

    BELL1("cloche", 3, 15, 3),
    BELL2("cloche", 3, 15, 3),
    BELL3("cloche", 3, 15, 3),
    BELL4("cloche", 3, 15, 3),

    STAR1("etoile", 5, 30, 4),
    STAR2("etoile", 5, 30, 4),
    STAR3("etoile", 5, 30, 4),

    CLOVER1("trefle", 10, 60, 5),
    CLOVER2("trefle", 10, 60, 5),

    SEVEN("7", 30, 200, 6);

    private final String emoji;
    private final int multi2;
    private final int multi3;
    private final int id;

    SymboleSlotMachineRoueTrois(String emoji, int multi2, int multi3, int id) {
        this.emoji = emoji;
        this.multi2 = multi2;
        this.multi3 = multi3;
        this.id = id;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getMulti2() {
        return multi2;
    }

    public int getMulti3() {
        return multi3;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return emoji;
    }
}
