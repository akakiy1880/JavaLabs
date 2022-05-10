package commands;

import collection.HumanBeing;
import collectionworker.CollectionManager;
import interfaces.CommandWithArguments;
import logging.Log;

import java.util.Scanner;

public class RemoveById implements CommandWithArguments {

    private CollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    RemoveById (CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public void execute() {
        try {
            if (arguments.length == 0) {
                throw new NullPointerException();
            }
            HumanBeing humanBeing1 = null;
            for (HumanBeing humanBeing : collectionManager.getHumanBeings()) {
                if (humanBeing.getId() ==Long.parseLong(arguments[0])) {
                    humanBeing1 = humanBeing;
                }
            }
            collectionManager.getHumanBeings().remove(humanBeing1);
        }  catch (NullPointerException e) {
            Log.warning("Введите корректный аргумент");
        }
    }

    @Override
    public String getDescription() {
        return "Удалить элемент из коллекции по его id";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }

}
