package commands;

import interfaces.Command;
import packer.Pack;
import server.CollectionManager;

public class Shuffle implements Command {

    CollectionManager collectionManager;

    Shuffle(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.randDragon();
        pack.pack("Объекты перемешаны\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Перемешать объекты коллекции";
    }
}
