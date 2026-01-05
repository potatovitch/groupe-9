package test;

import main.Argent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgentTest {

    private Argent argent;

    @BeforeEach
    public void setUp() {
        argent = new Argent();
    }

    @Test
    public void testSoldeInitial() {
        assertEquals(1000.0, argent.getSolde(), "Le solde initial doit être de 1000€");
    }

    @Test
    public void testSetSolde() {
        argent.setSolde(500.0);
        assertEquals(500.0, argent.getSolde());
    }

    @Test
    public void testAjouterSolde() {
        argent.ajouterSolde(250.0);
        assertEquals(1250.0, argent.getSolde());
    }

    @Test
    public void testRetirerSolde_Suffisant() {
        boolean result = argent.retirerSolde(300.0);
        assertTrue(result);
        assertEquals(700.0, argent.getSolde());
    }

    @Test
    public void testAppliquerBonus() {
        argent.AppliquerBonus(100.0);
        assertEquals(1100.0, argent.getSolde());
    }

    @Test
    public void testAppliquerMalus_Suffisant() {
        argent.AppliquerMalus(200.0);
        assertEquals(800.0, argent.getSolde());
    }

    @Test
    public void testToStringFormat() {
        assertEquals("1000.0€", argent.toString());
    }

    @Test
    public void testRetirerSolde_Insuffisant() {
        boolean result = argent.retirerSolde(2000.0);
        assertFalse(result, "Le retrait doit échouer si le solde est insuffisant.");
        assertEquals(1000.0, argent.getSolde(), "Le solde ne doit pas changer.");
    }

    @Test
    public void testAjouterSolde_Negatif() {
        argent.ajouterSolde(-100.0);
        assertEquals(900.0, argent.getSolde(), "L'ajout négatif agit comme un retrait.");
    }

    @Test
    public void testAppliquerBonus_Negatif() {
        argent.AppliquerBonus(-50.0);
        assertEquals(950.0, argent.getSolde());
    }

    @Test
    public void testAppliquerMalus_TropGrand() {
        argent.AppliquerMalus(1500.0);
        assertEquals(1000.0, argent.getSolde(), "Aucun changement si malus > solde.");
    }


}
