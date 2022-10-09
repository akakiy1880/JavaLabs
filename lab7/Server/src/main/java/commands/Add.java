package commands;

import bd.BDCollection;
import interfaces.Command;
import packer.Pack;
import server.CollectionManager;

public class Add implements Command {
    private CollectionManager collectionManager;
    private BDCollection BDCollection;

    public Add(CollectionManager collectionManager, BDCollection BDCollection) {
        this.collectionManager = collectionManager;
        this.BDCollection = BDCollection;
    }

    @Override
    public Pack execute(Pack pack) {
        BDCollection.add(pack.getUser(), pack.getDragon());
        collectionManager.insert(pack.getDragon());
        pack.pack("Дракон добавлен\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию";
    }
}
