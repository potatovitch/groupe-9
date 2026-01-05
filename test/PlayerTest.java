package test;

import main.Argent;
import main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Alice");
    }

    @Test
    public void testGetName() {
        assertEquals("Alice", player.getName());
    }

    @Test
    public void testSetName() {
        player.setName("Bob");
        assertEquals("Bob", player.getName());
    }

    @Test
    public void testInitialSolde() {
        Argent solde = player.getSolde();
        assertNotNull(solde, "Le solde ne doit pas être null.");
        assertEquals(1000.0, solde.getSolde(), "Le solde initial doit être de 1000€.");
    }

}
