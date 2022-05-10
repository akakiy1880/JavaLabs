package file;

import collection.*;
import logging.Log;

import java.util.Locale;

public class Validation {
    public boolean validation(String[] line, int lineNumber) {
        try {
            if (line[0].trim().equals("") || Long.parseLong(line[0]) <= 0) {
                Log.warning("Неверный формат id у " + lineNumber + " строки");
                return false;
            }
        } catch (NumberFormatException e) {
            Log.warning("Неверный формат id у " + lineNumber + " строки");
            return false;
        }

        if (line[1].trim().equals("") || line[1].trim().toLowerCase(Locale.ROOT).equals("none")) {
            Log.warning("Неверный формат name у " + lineNumber + " строки");
            return false;
        }

        try {
            Long.parseLong(line[2]);
        } catch (NumberFormatException e) {
            Log.warning("Неверный формат X у " + lineNumber + " строки");
            return false;
        }

        try {
            Long.parseLong(line[3]);
        } catch (NumberFormatException e) {
            Log.warning("Неверный формат Y у " + lineNumber + " строки");
            return false;
        }

        try {
            if (line[5].trim().equalsIgnoreCase("true") || line[5].trim().equalsIgnoreCase("false")) {
            } else {
                Log.warning("Неверный формат realHero у " + lineNumber + " строки");
                return false;
            }
        } catch (Exception ignored) {
            Log.warning("Неверный формат realHero у " + lineNumber + " строки");
            return false;
        }

        try {
            if (line[6].trim().equalsIgnoreCase("true") || line[6].trim().equalsIgnoreCase("false")) {
            } else {
                Log.warning("Неверный формат hasToothpick у " + lineNumber + " строки");
                return false;
            }
        } catch (Exception ignored) {
            Log.warning("Неверный формат hasToothpick у " + lineNumber + " строки");
            return false;
        }

        try {
            Integer.parseInt(line[7]);
        } catch (NumberFormatException e) {
            Log.warning("Неверный формат impactSpeed у " + lineNumber + " строки");
            return false;
        }

        try {
            WeaponType.valueOf(line[8]);
        } catch (IllegalArgumentException e) {
            Log.warning("Неверный формат weaponType у " + lineNumber + " строки");
            return false;
        }

        try {
            Mood.valueOf(line[9]);
        } catch (IllegalArgumentException e) {
            Log.warning("Неверный формат mood у " + lineNumber + " строки");
            return false;
        }

        if (line[10].trim().equals("") || line[10].trim().toLowerCase(Locale.ROOT).equals("none")) {
            Log.warning("Неверный формат nameCar у " + lineNumber + " строки");
            return false;
        }

        return true;
    }
}
