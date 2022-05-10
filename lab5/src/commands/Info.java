package commands;

import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;
import utils.CollectionInfo;

public class Info implements Command {
    private CollectionManager collectionManager;

    private CollectionInfo textFormatter;

    public Info(CollectionManager collectionManager) {

        this.collectionManager = collectionManager;
        textFormatter = new CollectionInfo();
    }

    @Override
    public void execute() {
        try {
            textFormatter.getInfoAboutCollection(collectionManager);
        } catch (NullPointerException ex) {
            Log.warning("В коллекции нет элементов");
        }
    }

    @Override
    public String getDescription() {
        return "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
