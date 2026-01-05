package test;

import main.SlotMachine;
import main.SymboleSlotMachineRoueUn;
import main.SymboleSlotMachineRoueDeux;
import main.SymboleSlotMachineRoueTrois;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlotMachineTest {
    @Test
    public void testEstTroisPareilTrue() {
        SlotMachine sm = new SlotMachine();
        sm.setSymbole1(SymboleSlotMachineRoueUn.SEVEN);
        sm.setSymbole2(SymboleSlotMachineRoueDeux.SEVEN);
        sm.setSymbole3(SymboleSlotMachineRoueTrois.SEVEN);

        assertTrue(sm.EstTroisPareil());
    }

    @Test
    public void testEstTroisPareilFalse() {
        SlotMachine sm = new SlotMachine();
        sm.setSymbole1(SymboleSlotMachineRoueUn.SEVEN);
        sm.setSymbole2(SymboleSlotMachineRoueDeux.LEMON1);
        sm.setSymbole3(SymboleSlotMachineRoueTrois.SEVEN);

        assertFalse(sm.EstTroisPareil());
    }

    @Test
    public void testEstDeuxPareilTrue() {
        SlotMachine sm = new SlotMachine();
        sm.setSymbole1(SymboleSlotMachineRoueUn.LEMON1);
        sm.setSymbole2(SymboleSlotMachineRoueDeux.LEMON1);
        sm.setSymbole3(SymboleSlotMachineRoueTrois.SEVEN);

        assertTrue(sm.EstDeuxPareil());
    }

    @Test
    public void testEstDeuxPareilFalse() {
        SlotMachine sm = new SlotMachine();
        sm.setSymbole1(SymboleSlotMachineRoueUn.LEMON1);
        sm.setSymbole2(SymboleSlotMachineRoueDeux.SEVEN);
        sm.setSymbole3(SymboleSlotMachineRoueTrois.CLOVER1);

        assertFalse(sm.EstDeuxPareil());
    }
}