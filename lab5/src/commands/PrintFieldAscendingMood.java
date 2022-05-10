package commands;

import collection.HumanBeing;
import collection.Mood;
import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;

import java.util.Comparator;
import java.util.Scanner;

public class PrintFieldAscendingMood implements Command {

    private CollectionManager collectionManager;
    private Scanner in;

    PrintFieldAscendingMood(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            collectionManager.getHumanBeings().stream().sorted(Comparator.comparingInt(o -> o.getMood().ordinal())).forEach(System.out::println);
                    //filter(human -> human.getMood() == Mood.valueOf(arguments[0])).count()
//            Log.info(collectionManager.getHumanBeings().peekFirst());
        } catch (Exception e) {
            Log.warning("Коллекция пуста");
        }
    }

    @Override
    public String getDescription() {
        return "вывести значения поля mood всех элементов в порядке возрастания";
    }
}
