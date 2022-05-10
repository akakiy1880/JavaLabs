package commands;

import collection.CollectionHumanBeing;
import collectionworker.CollectionManager;
import interfaces.Command;

public class Add implements Command {

    private CollectionManager collectionManager;
    CollectionHumanBeing collectionHumanBeing = new CollectionHumanBeing();

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.insert(collectionHumanBeing.createHumanBeing());
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию";
    }
}
