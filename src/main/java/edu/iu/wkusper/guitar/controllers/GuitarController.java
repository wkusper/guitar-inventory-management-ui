package edu.iu.wkusper.guitar.controllers;

import edu.iu.wkusper.guitar.model.Guitar;
import edu.iu.wkusper.guitar.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guitar")
public class GuitarController {
    @PostMapping("/add")
    public boolean add(@RequestBody Guitar data) {
        System.out.println(data.getSerialNumber() + "," + data.getBuilder() + "," + data.getModel() + "," + data.getType() + "," + data.getBackWood() + "," + data.getTopWood() + "," + data.getPrice());
        return true;
    }
    @GetMapping("/find/{serialNumber}")
    public Guitar find(@RequestBody String serialNumber) {
        try {
            InventoryRepository inventoryRepository = new InventoryRepository();
            return inventoryRepository.getGuitar(serialNumber);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/search")
    public List<Guitar> search(
            @RequestParam(required = false, defaultValue = "") String serialNumber,
            @RequestParam(required = false, defaultValue = "") String builder,
            @RequestParam(required = false, defaultValue = "")String model,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "") String backWood,
            @RequestParam(required = false, defaultValue = "") String topWood,
            @RequestParam(required = false, defaultValue = "0.0") double price
    ) {
        try {
            InventoryRepository inventoryRepository = new InventoryRepository();
            System.out.println(serialNumber + "," + builder + "," + model + "," + type + "," + backWood + "," + topWood + "," + price);
            return inventoryRepository.search(new Guitar(serialNumber, builder, model, type, backWood, topWood, price));
        } catch (Exception e) {
            return null;
        }
    }
}
