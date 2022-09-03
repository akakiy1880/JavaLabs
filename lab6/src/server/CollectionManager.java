package server;

import lib.dragon.*;
import server.logging.Log;

import java.time.LocalDate;
import java.util.*;

public class CollectionManager {
    public Stack<Dragon> dragons;
    private DragonFactory dragonFactory;
    private LocalDate creationCollectionDate;

    public CollectionManager() {
        creationCollectionDate = LocalDate.now();
        dragons = new Stack<>();
        dragonFactory = new DragonFactory();
    }

    public LocalDate getCreationCollectionDate() {
        return creationCollectionDate;
    }

    public Dragon insert(Dragon dragon) {
        int checkId = dragon.getId();
        for (Dragon val : getDragons())
            if (isElementInCollection(checkId)) checkId++;
        dragon.setId(checkId);
        dragons.push(dragon);
        return dragon;
    }

    public void reload(Dragon dragon) {
        int checkId = dragon.getId();
        for (int i = 0; i < dragons.size(); i++) {
            if (dragons.get(i).getId() == checkId) {
                dragons.set(i, dragon);
            }
        }
    }

    public Stack<Dragon> randDragon() {
        Collections.shuffle(dragons);
        return dragons;
    }

    public Stack<Dragon> getDragons() {
        return dragons;
    }

    public boolean isElementInCollection(long id) {
        if (id < 1) {
            return false;
        }
        for (Dragon vals : this.getDragons()) {
            if (vals.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return dragons.size();
    }

    public void updateById(long id, String field, String value) {
        switch (field) {
            case "name" -> {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).setName(value);
                Log.info("Поле изменено!");
            }
            case "coordinate_x" -> {
                if (value.equals("")) value = null;
                getElementById(id).getCoordinates().setX(Float.parseFloat(value));
                Log.info("Поле изменено!");
            }
            case "coordinate_y" -> {
                if (value.equals("")) value = null;
                getElementById(id).getCoordinates().setY(Integer.parseInt(value));
                Log.info("Поле изменено!");
            }
            case "age" -> {
                if (value.equals("")) {
                    getElementById(id).setAge(null);
                }
                getElementById(id).setAge(Long.parseLong(value));
                Log.info("Поле изменено!");
            }
            case "weight" -> {
                if (value.equals("")) value = null;
                getElementById(id).setWeight(Long.parseLong(value));
                Log.info("Поле изменено!");
            }
            case "speaking" -> {
                if (value.equals("")) value = null;
                getElementById(id).setSpeaking(Boolean.parseBoolean(value));
                Log.info("Поле изменено!");
            }
            case "character" -> {
                getElementById(id).setCharacter(DragonCharacter.valueOf(value.toUpperCase(Locale.ROOT)));
                Log.info("Поле изменено!");
            }
            case "killer" -> {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).getKiller().setName(value);
                Log.info("Поле изменено!");
            }
            case "passport" -> {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).getKiller().setPassportID(value);
                Log.info("Поле изменено!");
            }
            case "color_heir" -> {
                getElementById(id).getKiller().setHairColor(Color.valueOf(value.toUpperCase(Locale.ROOT)));
                Log.info("Поле изменено!");
            }
            case "nationality" -> {
                getElementById(id).getKiller().setNationality(Country.valueOf(value.toUpperCase(Locale.ROOT)));
                Log.info("Поле изменено!");
            }
            case "location_X" -> {
                if (value.equals("")) {
                    getElementById(id).setAge(null);
                }
                getElementById(id).getKiller().getLocation().setX(Double.parseDouble(value));
                Log.info("Поле изменено!");
            }
            case "location_Y" -> {
                if (value.equals("")) {
                    getElementById(id).setAge(null);
                }
                getElementById(id).getKiller().getLocation().setY(Integer.parseInt(value));
                Log.info("Поле изменено!");
            }
            case "location" -> {
                if (value.equals("")) throw new NullPointerException();
                getElementById(id).getKiller().getLocation().setName(value);
                Log.info("Поле изменено!");
            }
            case "stop" -> {
                Log.info("Объект изменен!");
            }
            default -> {
                Log.info("Значение поля введено неверно!");
            }
        }

    }

    public void removeById(Dragon dragon) {
        dragons.remove(dragon);
    }

    public void clear() {
        dragons.clear();
        dragonFactory.setId(1);
    }

    public Dragon getElementById(long id) {
        if (id < 1) {
            System.err.println("Введите корректный id!");
        }
        for (Dragon vals : dragons) {
            if (vals.getId() == id) {
                return vals;
            }
        }
        return null;
    }
}
