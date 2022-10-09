package commands;

import bd.BDCollection;
import interfaces.Command;
import packer.Pack;
import server.CollectionManager;

public class Clear implements Command {

    private CollectionManager collectionManager;
    BDCollection BDCollection;

    public Clear(CollectionManager collectionManager, BDCollection BDCollection) {
        this.collectionManager = collectionManager;
        this.BDCollection = BDCollection;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        BDCollection.clear();
        collectionManager.clear();
        pack.pack("Коллекция очищена!\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию";
    }
}
