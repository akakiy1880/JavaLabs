package commands;

import interfaces.CommandWithArguments;
import packer.Pack;
import server.CollectionManager;

public class GetDragon implements CommandWithArguments {
    CollectionManager collectionManager;
    String[] arg = {""};

    GetDragon(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.reload(pack.getDragon());
        return pack;
    }

    @Override
    public String getDescription() {
        return "getDragon";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arg = arguments;
    }
}
