package utils;

import collection.*;
import logging.Log;

import java.util.Scanner;

public class HumanBeingFieldReader {
    private Scanner in = new Scanner(System.in);

    public String readName() {
        String str;
        while (true) {
            Log.info("Введите имя: \n");
            str = in.nextLine();
            if (str.trim().equals("")) {
                Log.warning("Имя не может быть пустой строкой\n");
                continue;

            }
            if (str.trim().equals("null")) {
                Log.warning("Имя не может быть null\n");
                continue;
            }
            return str;
        }
    }

//    private String CheckNullAndClear(String str, String insert){
//        if (str.trim().equals("")) {
//            Log.warning( insert + " не может быть пустой строкой\n");
//        }
//        if (str.trim().equals("null")) {
//            Log.warning(insert + " не может быть null\n");
//        }
//        return str;
//    }


    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    public Long readCoordinateX() {
        Long x;
        while (true) {
            try {
                Log.info("Введите координату Х: \n");
                String str = in.nextLine();
                x = Long.parseLong(str.trim());
                return x;
            } catch (NumberFormatException e) {
                Log.warning("Координата Х должна быть числом\n");
            }
        }
    }

    public Long readCoordinateY() {
        long y;
        while (true) {
            try {
                Log.info("Введите координату Y: \n");
                String str = in.nextLine();
                y = Long.parseLong(str.trim());
                return y;
            } catch (NumberFormatException e) {
                Log.warning("Координата Y должна быть числом\n");
            }
        }
    }
    public boolean readHasToothpick() {
        while (true) {
            try {
                Log.info("Настоящий герой? \n");
                String str = in.nextLine();
                if (str.trim().equalsIgnoreCase("да")) {
                    return true;
                } else if (str.trim().equalsIgnoreCase("нет")) {
                    return false;
                }
            } catch (Exception ignored) {}
            Log.warning("Введите 'да' либо 'нет'.");
        }
    }

    public boolean readRealHero() {
        while (true) {
            try {
                Log.info("C зубочисткой в зубах: \n");
                String str = in.nextLine();
                if (str.trim().equalsIgnoreCase("да")) {
                    return true;
                } else if (str.trim().equalsIgnoreCase("нет")) {
                    return false;
                }
            } catch (Exception ignored) {}
            Log.warning("Введите 'да' либо 'нет'.");
        }
    }

    public Integer readImpactSpeed() {
        Integer impactSpeed;
        while (true) {
            try {
                Log.info("Введите скорость атаки (число): \n");
                String str = in.nextLine();
                impactSpeed = Integer.parseInt(str.trim());
                return impactSpeed;
            } catch (NumberFormatException e) {
                Log.warning("Cкорость атаки должна быть числом\n");
            }
        }
    }

    public WeaponType readWeaponType() {
        WeaponType weaponType;
        while (true) {
            try {
                Log.info("Выберите тип оружия: \n");
                for (WeaponType val : WeaponType.values()) {
                    Log.info(val.name());
                }
                String val = in.nextLine();
                if (val.trim().equals("")) {
                    Log.warning("Тип оружия не может быть пустой строкой\n");
                    continue;
                }
                if (val.trim().equals("null")) {
                    Log.warning("Тип оружия не может быть null\n");
                    continue;
                }
                weaponType = WeaponType.valueOf(val.trim().toUpperCase());
                return weaponType;
            } catch (IllegalArgumentException e) {
                Log.warning("Выберите значение из списка\n");
            }
        }
    }

    public Mood readMood() {
        Mood mood;
        while (true) {
            try {
                Log.info("Выберите настроение: \n");
                for (Mood val : Mood.values()) {
                    Log.info(val.name());
                }
                String val = in.nextLine();
                if (val.trim().equals("")) {
                    Log.warning("Настроение не может быть пустой строкой\n");
                    continue;

                }
                if (val.trim().equals("null")) {
                    Log.warning("Настроение не может быть null\n");
                    continue;
                }
                mood = Mood.valueOf(val.trim().toUpperCase());
                return mood;
            } catch (IllegalArgumentException e) {
                Log.warning("Выберите значение из списка\n");
            }
        }
    }

    public Car readCar() {
        return new Car(readCarName());
    }

    public String readCarName() {
        String carName;
        while (true) {
            Log.info("Введите марку машины: \n");
            carName = in.nextLine();
            if (carName.trim().equals("")) {
                Log.warning("Марка машины не может быть пустой строкой\n");
                continue;
            }
            if (carName.trim().equals("null")) {
                Log.warning("Марка машины не может быть null\n");
                continue;
            }
            return carName;
        }
    }

}
