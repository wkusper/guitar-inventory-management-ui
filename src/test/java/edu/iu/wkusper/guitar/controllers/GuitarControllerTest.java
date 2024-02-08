package edu.iu.wkusper.guitar.controllers;

import org.junit.jupiter.api.Test;
import edu.iu.wkusper.guitar.model.Guitar;

import static edu.iu.wkusper.guitar.model.Builder.*;
import static edu.iu.wkusper.guitar.model.Type.*;
import static edu.iu.wkusper.guitar.model.Wood.*;
import static org.junit.jupiter.api.Assertions.*;

class GuitarControllerTest {

    @Test
    void add() {
    GuitarController guitarController = new GuitarController();
    assertTrue(guitarController.add(new Guitar("123", FENDER, "Stratocaster", ELECTRIC, MAPLE, ALDER, 1499.99)));
    assertTrue(guitarController.add(new Guitar("456", GIBSON, "Les Paul", ELECTRIC, MAHOGANY, MAHOGANY, 2499.99)));
    }

    boolean sameGuitar(Guitar guitar1, Guitar guitar2) {
        return guitar1.getSerialNumber().equals(guitar2.getSerialNumber()) &&
                guitar1.getBuilder().equals(guitar2.getBuilder()) &&
                guitar1.getModel().equals(guitar2.getModel()) &&
                guitar1.getType().equals(guitar2.getType()) &&
                guitar1.getBackWood().equals(guitar2.getBackWood()) &&
                guitar1.getTopWood().equals(guitar2.getTopWood()) &&
                guitar1.getPrice() == guitar2.getPrice();
    }
    @Test
    void find() {
        GuitarController guitarController = new GuitarController();
        assertTrue(sameGuitar(new Guitar("123", FENDER, "Stratocaster", ELECTRIC, MAPLE, ALDER, 1499.99), guitarController.find("123")));
        assertTrue(sameGuitar(new Guitar("456", GIBSON, "Les Paul", ELECTRIC, MAHOGANY, MAHOGANY, 2499.99), guitarController.find("456")));
    }

    @Test
    void search() {
        GuitarController guitarController = new GuitarController();
        assertEquals(4, guitarController.search("", "", "", "", "", "", 0).size());
        assertEquals(1, guitarController.search("123", "", "", "", "", "", 0).size());
        assertEquals(1, guitarController.search("", "Fender", "", "", "", "", 0).size());
        assertEquals(1, guitarController.search("", "", "Stratocaster", "", "", "", 0).size());
        assertEquals(2, guitarController.search("", "", "", "electric", "", "", 0).size());
        assertEquals(1, guitarController.search("", "", "", "", "mahogany", "", 0).size());
        assertEquals(1, guitarController.search("", "", "", "", "", "alder", 0).size());
        assertEquals(1, guitarController.search("", "Gibson", "", "", "", "", 0).size());
        assertEquals(1, guitarController.search("", "", "", "electric", "mahogany", "", 0).size());
    }
}