package commands;

import interfaces.Command;
import logging.Log;

public class Exit implements Command {

    @Override
    public void execute() {
        Log.info("Завершение работы программы!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Завершить программу (без сохранения в файл)";
    }

}
