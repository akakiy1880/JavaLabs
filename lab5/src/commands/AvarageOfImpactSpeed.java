package commands;

import collection.HumanBeing;
import collectionworker.CollectionManager;
import interfaces.Command;
import logging.Log;


import java.util.Scanner;

public class AvarageOfImpactSpeed implements Command {

    private CollectionManager collectionManager;
    private Scanner in;

    AvarageOfImpactSpeed(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            Log.info(collectionManager.getHumanBeings().stream().mapToInt(HumanBeing::getImpactSpeed).sum() / collectionManager.getHumanBeings().size());
        } catch (Exception e) {
            Log.warning("Коллекция пуста");
        }
    }

    @Override
    public String getDescription() {
        return "вывести среднее значение поля impactSpeed для всех элементов коллекции";
    }
}
