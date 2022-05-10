package file;

import collection.*;
import collectionworker.CollectionManager;
import logging.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
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
                if (line.length != 11) {
                    Log.warning("Было задано неправильное количество полей для объекта коллекции c " + line[0]);
                    continue;
                }
                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].replaceFirst(".+=", "");
                }
                Validation validation = new Validation();
                if (validation.validation(line, lineNumber)) {
                    Long id = Long.parseLong(line[0]);
                    Coordinates coordinates = new Coordinates(Long.parseLong(line[2]), Long.parseLong(line[3]));
                    LocalDateTime creationDate = LocalDateTime.parse(line[4]);
                    boolean realHero = Boolean.parseBoolean(line[5]);
                    boolean hasToothpick = Boolean.parseBoolean(line[6]);
                    Integer impactSpeed = Integer.parseInt(line[7]);
                    WeaponType weaponType = WeaponType.valueOf(line[8]);
                    Mood mood = Mood.valueOf(line[9]);
                    Car car = new Car(line[10]);
                    HumanBeing humanBeing = new HumanBeing(id, line[1], coordinates, creationDate,
                            realHero, hasToothpick, impactSpeed, weaponType, mood, car);
                    collectionManager.insert(humanBeing);
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {

        }
    }
}
