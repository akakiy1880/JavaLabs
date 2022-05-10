package commands;

import collectionworker.CollectionManager;
import interfaces.Command;
import utils.CollectionInfo;

public class Show implements Command {

    private CollectionManager collectionManager;
    private CollectionInfo collectionInfo;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        collectionInfo = new CollectionInfo();
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionInfo.show(collectionManager);
    }

    @Override
    public String getDescription() {
        return "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
