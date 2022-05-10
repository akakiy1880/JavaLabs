package collectionworker;

import collection.*;
import logging.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    public ArrayDeque<HumanBeing> humanBeings = new ArrayDeque<>();
    private CollectionHumanBeing collectionHumanBeing;
    private LocalDate creationCollectionDate;

    public CollectionManager() {
        humanBeings = new ArrayDeque<>();
        collectionHumanBeing = new CollectionHumanBeing();
        try{

        Path file = Paths.get(System.getenv("FILE_PATH"));

        BasicFileAttributes attr =
                Files.readAttributes(file, BasicFileAttributes.class);
        creationCollectionDate = Instant.ofEpochMilli(attr.creationTime().toMillis()).atZone(ZoneId.systemDefault()).toLocalDate();;
        }
        catch (IOException e){
            creationCollectionDate = LocalDate.now();
        }
    }

    public LocalDate getCreationCollectionDate() {
        return creationCollectionDate;
    }

    public HumanBeing insert(HumanBeing humanBeing) {
        long checkId = humanBeing.getId();
        for (HumanBeing val : getHumanBeings())
            if (isElementInCollection(checkId)) checkId++;
        humanBeing.setId(checkId);
        humanBeings.add(humanBeing);
        return humanBeing;
    }


    public ArrayDeque<HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    public boolean isElementInCollection(long id) {
        if (id < 1) {
            return false;
        }
        for (HumanBeing values : this.getHumanBeings()) {
            if (values.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return humanBeings.size();
    }

    public void updateById(long id, String field, String value) {
        switch (field) {
            case "name": {
                if (value.trim().equals(""))   throw new NullPointerException();
                getElementById(id).setName(value);
                Log.info("Поле изменено!");
                break;
            }
            case "coordinate_x": {
                if (value.trim().equals(""))  throw new NullPointerException();
                getElementById(id).getCoordinates().setX(Long.parseLong(value));
                Log.info("Поле изменено!");
                break;
            }
            case "coordinate_y": {
                if (value.trim().equals(""))  throw new NullPointerException();
                getElementById(id).getCoordinates().setY(Long.parseLong(value));
                Log.info("Поле изменено!");
                break;
            }

            case "real_hero": {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).setRealHero(Boolean.parseBoolean(value));
                Log.info("Поле изменено!");
                break;
            }

            case "has_toothpick": {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).setHasToothpick(Boolean.parseBoolean(value));
                Log.info("Поле изменено!");
                break;
            }

            case "impact_speed": {
                if (value.trim().equals(""))  throw new NullPointerException();
                getElementById(id).setImpactSpeed(Integer.parseInt(value));
                Log.info("Поле изменено!");
                break;
            }

            case "weapon_type": {
                getElementById(id).setWeaponType(WeaponType.valueOf(value.toUpperCase(Locale.ROOT)));
                Log.info("Поле изменено!");
                break;
            }

            case "mood": {
                getElementById(id).setMood(Mood.valueOf(value.toUpperCase(Locale.ROOT)));
                Log.info("Поле изменено!");
                break;
            }

            case "car_name": {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).getCar().setCarName(value);
                Log.info("Поле изменено!");
                break;
            }

            case "stop": {
                Log.info("Объект изменен!");
                break;
            }
            default: {
                Log.info("Значение поля введено неверно!");
                break;
            }
        }

    }

    public void removeById(long id) {
        humanBeings.remove(id);
    }

    public void removeFirst() {
        humanBeings.removeFirst();
    }

    public void removeHead() {
        humanBeings.pollFirst();
    }

    public void clear() {
        humanBeings.clear();
        collectionHumanBeing.setId(1L);
    }

    public HumanBeing getElementById(long id) {
        if (id < 1) {
            Log.warning("Введите корректный id!");
        }
        for (HumanBeing values : humanBeings) {
            if (values.getId() == id) {
                return values;
            }
        }
        return null;
    }
}

