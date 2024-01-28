package repository;

import edu.iu.wkusper.guitar.model.Guitar;
import edu.iu.wkusper.guitar.repository.InventoryRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryRepositoryTest {
    InventoryRepository inventoryRepository = new InventoryRepository();
    Guitar guitar1 = new Guitar("123", "Fender", "Stratocaster", "electric", "maple", "alder", 1499.99);
    Guitar guitar2 = new Guitar("456", "Gibson", "Les Paul", "electric", "mahogany", "mahogany", 2499.99);
    Guitar guitar3 = new Guitar("789", "Martin", "D-28", "acoustic", "rosewood", "rosewood", 2999.99);
    Guitar guitar4 = new Guitar("101", "Taylor", "914ce", "acoustic", "rosewood", "spruce", 3999.99);



    @Test
    @Order(1)
    void addGuitar() throws IOException {
        inventoryRepository.addGuitar(guitar1);
        inventoryRepository.addGuitar(guitar2);
        inventoryRepository.addGuitar(guitar3);
        inventoryRepository.addGuitar(guitar4);
        assertEquals(4, inventoryRepository.search(new Guitar("", "", "", "", "", "", 0)).size());}

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
    @Order(2)
    void getGuitar() {
        assertTrue(sameGuitar(guitar1, inventoryRepository.getGuitar("123")));
        assertTrue(sameGuitar(guitar3, inventoryRepository.getGuitar("789")));
        }
    @Test
    @Order(3) //this test would always before the add test, so it would always fail even if it is correct
    void search() throws IOException {
        assertEquals(4, inventoryRepository.search(new Guitar("", "", "", "", "", "", 0)).size());
        assertEquals(1, inventoryRepository.search(new Guitar("123", "", "", "", "", "", 0)).size());
        assertEquals(1, inventoryRepository.search(new Guitar("", "Fender", "", "", "", "", 0)).size());
        assertEquals(1, inventoryRepository.search(new Guitar("", "", "Stratocaster", "", "", "", 0)).size());
        assertEquals(2, inventoryRepository.search(new Guitar("", "", "", "electric", "", "", 0)).size());
        assertEquals(2, inventoryRepository.search(new Guitar("", "", "", "", "rosewood", "", 0)).size());
        assertEquals(1, inventoryRepository.search(new Guitar("", "", "", "", "", "alder", 0)).size());
        assertEquals(1, inventoryRepository.search(new Guitar("", "Gibson", "", "", "", "", 0)).size());
        assertEquals(2, inventoryRepository.search(new Guitar("", "", "", "acoustic", "rosewood", "", 0)).size());
        assertEquals(0, inventoryRepository.search(new Guitar("", "", "", "bass", "", "", 0)).size());
    }
}