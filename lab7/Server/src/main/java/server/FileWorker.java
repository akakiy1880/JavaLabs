package server;

import dragon.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class FileWorker {
    String path = System.getenv("FILE_PATH");
    private CollectionManager collectionManager;

    public FileWorker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void fromCSVtoObj() {
        try {
            int lineNumber = 1;
            File fileCsv = new File(path);
            Scanner file = new Scanner(fileCsv);
            while (file.hasNextLine()) {
                String[] line = file.nextLine().trim().split(", ");
                if (line.length != 16) {
                    System.err.println("Было задано неправильное количество полей для объекта коллекции c " + line[0]);
                    continue;
                }
                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].replaceFirst(".+=", "");
                }
                Validation validation = new Validation();
                if (validation.validation(line, lineNumber)) {
                    Coordinates coordinates = new Coordinates(Float.parseFloat(line[2]), Integer.parseInt(line[3]));
                    Date creationDate = Date.valueOf(LocalDate.parse(line[4]));
                    DragonCharacter dragonCharacter = DragonCharacter.valueOf(line[8]);
                    Color color = Color.valueOf(line[11]);
                    Country country = Country.valueOf(line[12]);
                    Location location = new Location(Double.parseDouble(line[13]), Integer.parseInt(line[14]), line[15]);
                    Person person = new Person(line[9], line[10], color, country, location);
                    Dragon dragon = new Dragon(Integer.parseInt(line[0]), line[1], coordinates, creationDate,
                            Long.parseLong(line[5]), Long.parseLong(line[6]), Boolean.parseBoolean(line[7]), dragonCharacter, person);
                    collectionManager.insert(dragon);
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
