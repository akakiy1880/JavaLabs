package commands;

import dragon.Dragon;
import interfaces.Command;
import packer.Pack;
import server.CollectionManager;

import java.util.Stack;

public class RemoveGreater implements Command {

    private CollectionManager collectionManager;

    RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.insert(pack.getDragon());
        Stack<Dragon> needToRemoveDragons = new Stack<>();
        for (Dragon dragon : collectionManager.getDragons()) {
            if (dragon.compareTo(pack.getDragon()) > 0) {
                needToRemoveDragons.add(dragon);
            }
        }
        collectionManager.getDragons().removeAll(needToRemoveDragons);
        pack.pack("Объекты превышающие введённый - удалены\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Удалить из коллекции все элементы, превышающие заданный";
    }
}
