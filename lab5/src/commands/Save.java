package commands;

import collection.Coordinates;
import collection.HumanBeing;
import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Locale;

public class Save implements Command {

    private CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        File file = new File("../../src/files/1.csv");
        try (FileOutputStream outputStream = new FileOutputStream(file)){
            for (HumanBeing value : collectionManager.getHumanBeings()) {
                Long id = value.getId();
                String name = value.getName();
                Coordinates coordinates = value.getCoordinates();
                LocalDateTime creationDate = value.getCreationDate();
                boolean realHero = value.getRealHero();
                boolean hasToothpick = value.getHasToothpick();
                Integer impactSpeed = value.getImpactSpeed();
                String weaponType = value.getWeaponType().toString().toUpperCase(Locale.ROOT);
                String mood = value.getMood().toString().toUpperCase(Locale.ROOT);
                String carName = value.getCar().getName();

                outputStream.write("id=%d, name=%s, x=%d, y=%d, date=%s, realHero=%b, hasToothpick=%b, impactSpeed=%d, weaponType=%s, mood=%s, carName=%s".formatted(id,name, coordinates.getX(), coordinates.getY(), creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, carName).getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        } catch (FileNotFoundException e) {
            Log.error("Ошибка. Отсутствует доступ к файлу." + e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.critical("Ошибка");
        }
    }

    @Override
    public String getDescription() {
        return "Сохранить коллекцию в файл";
    }
}
