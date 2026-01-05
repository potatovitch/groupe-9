package test;

import main.Tools;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ToolsTest {

    @Test
    public void testArrayToString_NormalCase() {
        int[] input = {1, 2, 3, 4};
        String expected = "1234";
        assertEquals(expected, Tools.arrayToString(input));
    }

    @Test
    public void testArrayToString_EmptyArray() {
        int[] input = {};
        String expected = "";
        assertEquals(expected, Tools.arrayToString(input));
    }

    @Test
    public void testEstUnChiffre_ValidDigit() {
        assertTrue(Tools.estUnChiffre("5"));
    }

    @Test
    public void testEstUnChiffre_InvalidCharacter() {
        assertFalse(Tools.estUnChiffre("a"));
    }

    @Test
    public void testEstUnChiffre_MultipleCharacters() {
        assertFalse(Tools.estUnChiffre("12"));
    }

    @Test
    public void testTailleEntreeUser_ValidInput() {
        String input = "test\n";
        setSystemInput(input);
        String result = Tools.tailleEntreeUser(10, "Entrez texte", "Erreur");
        assertEquals("test", result);
    }

    @Test
    public void testTailleEntreeUser_InvalidThenValid() {
        String input = "too_long_input\nok\n";
        setSystemInput(input);
        String result = Tools.tailleEntreeUser(5, "Message", "Erreur");
        assertEquals("ok", result);
    }

    @Test
    public void testIntervalleEntreeUser_ValidNumber() {
        String input = "5\n";
        setSystemInput(input);
        String result = Tools.intervalleEntreeUser(1, 10, "Entrez un nombre");
        assertEquals("5", result);
    }

    @Test
    public void testIntervalleEntreeUser_InvalidThenValid() {
        String input = "100\n3\n";
        setSystemInput(input);
        String result = Tools.intervalleEntreeUser(1, 10, "Entrez un nombre");
        assertEquals("3", result);
    }

    @Test
    public void testIntervalleEntreeUserCharUnique_ValidChar() {
        String input = "2\n";
        setSystemInput(input);
        String result = Tools.intervalleEntreeUserCharUnique(0, 4, "Choisissez");
        assertEquals("2", result);
    }

    @Test
    public void testIntervalleEntreeUserCharUnique_InvalidThenValid() {
        String input = "a\n5\n1\n";
        setSystemInput(input);
        String result = Tools.intervalleEntreeUserCharUnique(0, 4, "Choix");
        assertEquals("1", result);
    }

    private void setSystemInput(String input) { // simule l'entrée utilisateur du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
