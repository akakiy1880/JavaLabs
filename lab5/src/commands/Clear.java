package commands;

import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;

public class Clear implements Command {

    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.clear();
        Log.info("Коллекция очищена!");
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию";
    }
}
