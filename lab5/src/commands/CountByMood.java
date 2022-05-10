package commands;


import collection.Mood;
import collectionworker.CollectionManager;
import interfaces.CommandWithArguments;
import logging.Log;

import java.util.Locale;
import java.util.Scanner;

public class CountByMood implements CommandWithArguments {

    private CollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    CountByMood(CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public void execute() {
        try {
            if (arguments.length == 0) {
                throw new NullPointerException();
            }
            Log.info("Количество элементов с mood " + arguments[0].toUpperCase(Locale.ROOT) +" " +collectionManager.getHumanBeings().stream(
            ).filter(human -> human.getMood() == Mood.valueOf(arguments[0].toUpperCase(Locale.ROOT))).count());
        }  catch (NullPointerException e) {
            Log.warning("Введите корректный аргумент");
        }
        catch (IllegalArgumentException ex){
            Log.warning("Такого настроения не существует");
        }
        catch (Exception ex){
            Log.critical("Все таки сломал");
        }
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля mood которых равно заданному";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }

}
