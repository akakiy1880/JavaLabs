package commands;

import dragon.Dragon;
import interfaces.Command;
import packer.Pack;
import server.CollectionManager;

public class AverageOfWeight implements Command {
    private CollectionManager collectionManager;

    AverageOfWeight(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        int i = 0;
        double srWeight = 0.0;
        for (Dragon val : collectionManager.getDragons()) {
            srWeight += val.getWeight();
            i++;
        }
        response = String.valueOf(srWeight / i);
        pack.pack(response + "\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Средний вес всех драконов в коллекции";
    }
}
