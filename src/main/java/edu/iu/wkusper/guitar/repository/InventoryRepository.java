package edu.iu.wkusper.guitar.repository;

import edu.iu.wkusper.guitar.model.Guitar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InventoryRepository {

    private List<Guitar> guitars = new ArrayList<>();

    public void addGuitar(Guitar guitar) {
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber) {
        return guitars.stream()
                .filter(guitar -> guitar.getSerialNumber().equals(serialNumber))
                .findFirst()
                .orElse(null);
    }

    public List<Guitar> search(Guitar searchCriteria) {
        return guitars.stream()
                .filter(guitar -> (searchCriteria.getSerialNumber() == null || guitar.getSerialNumber().equals(searchCriteria.getSerialNumber())) &&
                        (searchCriteria.getPrice() == 0 || guitar.getPrice() == searchCriteria.getPrice()) &&
                        (searchCriteria.getBuilder() == null || guitar.getBuilder().equals(searchCriteria.getBuilder())) &&
                        (searchCriteria.getModel() == null || guitar.getModel().equals(searchCriteria.getModel())) &&
                        (searchCriteria.getType() == null || guitar.getType().equals(searchCriteria.getType())) &&
                        (searchCriteria.getBackWood() == null || guitar.getBackWood().equals(searchCriteria.getBackWood())) &&
                        (searchCriteria.getTopWood() == null || guitar.getTopWood().equals(searchCriteria.getTopWood())))
                .collect(Collectors.toList());
    }
}