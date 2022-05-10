package utils;

import collection.*;
import collection.HumanBeing;
import collectionworker.CollectionManager;
import logging.Log;

import java.util.Arrays;

public class CollectionInfo {
    public void getInfoAboutCollection(CollectionManager collectionManager) {
        Log.info("Дата создания коллекции " + collectionManager.getCreationCollectionDate());
        Log.info("Количество элементов в коллекции " + collectionManager.getHumanBeings().size());
    }

    public void show(CollectionManager collectionManager){
        if (collectionManager.getHumanBeings().size() == 0) {
            Log.info("Коллекция пуста!");
        } else {
            for (HumanBeing values : collectionManager.getHumanBeings()) {
                Log.info(values.toString());
            }
        }
    }

    public String getFieldsName(HumanBeing humanBeing) {
        return "Список всех полей:\n" +
                "name(String): %s\ncoordinate_x(Long): %d\ncoordinate_y(Long): %d\nrealHero(boolean): %b\nhas_toothpick(boolean): %b\nimpact_speed(Integer): %d\nweapon_type(%s): %s \nmood(%s): %s\ncar_name(String): %s".formatted(humanBeing.getName(),
                humanBeing.getCoordinates().getX(), humanBeing.getCoordinates().getY(),
                humanBeing.getRealHero(), humanBeing.getHasToothpick(),  humanBeing. getImpactSpeed(),
                Arrays.toString(WeaponType.values()),humanBeing.getWeaponType(),
                Arrays.toString(Mood.values()), humanBeing.getMood(),  humanBeing.getCar().getName());

    }
}