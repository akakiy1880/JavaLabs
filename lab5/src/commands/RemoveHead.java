package commands;

import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;


import java.util.Scanner;

public class RemoveHead implements Command {

    private CollectionManager collectionManager;
    private Scanner in;

    RemoveHead(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            if (collectionManager.getSize() <= 0){
                Log.warning("Коллекция пуста");
            }else {
            Log.info("Из коллекции был удален 1 элемент.\n"+collectionManager.getHumanBeings().peekFirst());
            collectionManager.removeHead();}
        } catch (Exception e) {
            Log.warning("Коллекция пуста");
        }
    }

    @Override
    public String getDescription() {
        return "Вывести первый элемент коллекции и удалить его";
    }
}
