package commands;

import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;


import java.util.Scanner;

public class ShowHead implements Command {

    private CollectionManager collectionManager;
    private Scanner in;

    ShowHead(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            Log.info(collectionManager.getHumanBeings().peekFirst());
        } catch (Exception e) {
            Log.warning("Коллекция пуста");
        }
    }

    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции";
    }
}
