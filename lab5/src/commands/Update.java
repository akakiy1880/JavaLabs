package commands;

import collection.HumanBeing;
import collectionworker.CollectionManager;
import interfaces.CommandWithArguments;
import logging.Log;
import utils.CollectionInfo;

import java.util.Scanner;

public class Update implements CommandWithArguments {
    private String[] arguments;
    private CollectionManager collectionManager;
    private Scanner in;
    private CollectionInfo collectionInfo;

    public Update(CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
        collectionInfo = new CollectionInfo();
    }

    @Override
    public void execute() {
        try {
            if (arguments.length == 0) {
                throw new NullPointerException();
            }
            if (collectionManager.isElementInCollection(Long.parseLong(arguments[0]))) {
                HumanBeing humanBeing = collectionManager.getElementById(Long.parseLong(arguments[0]));
                Log.info(collectionInfo.getFieldsName(humanBeing));
                Log.info("Введите stop, когда захотите прервать изменение элемента коллекции!");
                Log.info("Введите значения полей для элемента коллекции: ");
                String[] commandWords = new String[0];
                do {
                    try {
                        commandWords = in.nextLine().trim().split("\\s+");
                        if (commandWords.length == 1) {
                            collectionManager.updateById(Long.parseLong(arguments[0]), commandWords[0], "");
                        } else {
                            collectionManager.updateById(Long.parseLong(arguments[0]), commandWords[0], commandWords[1]);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        Log.warning("Не указано поле или значение!");
                    }
                } while (!commandWords[0].equals("stop"));

            } else {
                Log.warning("Элемента с данным id нет в коллекции!");
            }
        } catch (IndexOutOfBoundsException e2) {
            Log.warning("Не указаны все аргументы команды!");
        } catch (NumberFormatException e3) {
            Log.warning("Формат аргумента не соответствует целочисленному ");
        } catch (NullPointerException e4) {
            Log.warning("Не указаны все аргументы команды!");
        }
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
