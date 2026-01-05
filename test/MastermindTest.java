package test;

import main.Mastermind;
import main.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MastermindTest {

    private Mastermind mastermind;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        mockPlayer = new Player("TestPlayer");
        mastermind = new Mastermind(mockPlayer);
    }

    @Test
    public void testGenerateCode_LengthAndRange() {
        int[] code = mastermind.generateCode();
        assertEquals(4, code.length, "Le code doit contenir 4 chiffres.");
        for (int digit : code) {
            assertTrue(digit >= 1 && digit <= 9, "Chaque chiffre doit être entre 1 et 9.");
        }
    }

    @Test
    public void testArrayToString() {
        int[] code = {1, 2, 3, 4};
        assertEquals("1234", Mastermind.arrayToString(code));
    }

    @Test
    public void testBonnePlaceLettre_AllCorrect() {
        // Injecter un code connu
        mastermind = new Mastermind(mockPlayer);
        mastermind.generateCode();
        mastermind.getCode()[0] = 1;
        mastermind.getCode()[1] = 2;
        mastermind.getCode()[2] = 3;
        mastermind.getCode()[3] = 4;


        // L’appel ne retourne rien, mais il imprime 🟩🟩🟩🟩
        mastermind.bonnePlaceLettre("1234");

    }


    @Test
    public void testArrayToString_EmptyArray() {
        assertEquals("", Mastermind.arrayToString(new int[]{}));
    }

    @Test
    public void testBonnePlaceLettre_OnlyMisplaced() {
        mastermind.getCode()[0] = 1;
        mastermind.getCode()[1] = 2;
        mastermind.getCode()[2] = 3;
        mastermind.getCode()[3] = 4;

        mastermind.bonnePlaceLettre("4321");
        // Devrait afficher que des 🟨
    }
}
