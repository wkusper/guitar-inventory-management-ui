package edu.iu.wkusper.guitar.repository;

import edu.iu.wkusper.guitar.model.Guitar;
import edu.iu.wkusper.guitar.model.Builder;
import edu.iu.wkusper.guitar.model.Type;
import edu.iu.wkusper.guitar.model.Wood;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class InventoryRepository {

    private static final String NEW_LINE = System.lineSeparator();

    private static void appendToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    public void addGuitar(Guitar guitar) throws IOException {
        Path path = Paths.get("guitars_database.txt");
        String guitarInfo = guitar.getSerialNumber() + "," + guitar.getBuilder().toString() + "," + guitar.getModel() + ","
                + guitar.getType().toString() + "," + guitar.getBackWood().toString() + "," +
                guitar.getTopWood().toString() + "," + guitar.getPrice();
        appendToFile(path, guitarInfo + NEW_LINE);
    }
    public Guitar getGuitar(String serialNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("guitars_database.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] guitarData = line.split(",");
                if (guitarData[0].equals(serialNumber)) {
                    return new Guitar(guitarData[0], Builder.valueOf(guitarData[1].toUpperCase()), guitarData[2],
                            Type.valueOf(guitarData[3].toUpperCase()), Wood.valueOf(guitarData[4].toUpperCase()),
                            Wood.valueOf(guitarData[5].toUpperCase()), Double.parseDouble(guitarData[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }
    public List<Guitar> search(Guitar searchGuitar) throws IOException {
        List<Guitar> similarGuitars = new ArrayList<>();
        Path path = Paths.get("guitars_database.txt");
        List<String> guitars = Files.readAllLines(path);
        for (String guitar : guitars) {
            String[] guitarData = guitar.split(",");
            if (searchGuitar.getSerialNumber().equals("") || searchGuitar.getSerialNumber().equals(guitarData[0])) {
                if (searchGuitar.getBuilder() == null || searchGuitar.getBuilder() == Builder.ANY || searchGuitar.getBuilder().toString().equals(guitarData[1])) {
                    if (searchGuitar.getModel().equals("") || searchGuitar.getModel().equals(guitarData[2])) {
                        if (searchGuitar.getType() == null || searchGuitar.getType().toString().equals(guitarData[3])) {
                            if (searchGuitar.getBackWood() == null || searchGuitar.getBackWood().toString().equals(guitarData[4])) {
                                if (searchGuitar.getTopWood() == null || searchGuitar.getTopWood().toString().equals(guitarData[5])) {
                                    if (searchGuitar.getPrice() == 0 || searchGuitar.getPrice() == Double.parseDouble(guitarData[6])) {
                                        similarGuitars.add(new Guitar(guitarData[0], Builder.valueOf(guitarData[1].toUpperCase()), guitarData[2],
                                                Type.valueOf(guitarData[3].toUpperCase()), Wood.valueOf(guitarData[4].toUpperCase()),
                                                Wood.valueOf(guitarData[5].toUpperCase()), Double.parseDouble(guitarData[6])));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return similarGuitars;
    }
}
