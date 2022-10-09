package commands;

import interfaces.Command;
import packer.Pack;
import server.CollectionManager;

public class Exit implements Command {

    CollectionManager collectionManager;

    Exit(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
//        collectionManager.save;
        pack.pack("Вы откллючились от сервера\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Завершить программу (без сохранения в файл)";
    }

}
