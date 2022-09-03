package server.commands;

import lib.Pack;
import server.interfaces.CommandWithArguments;

public class ExecuteScript implements CommandWithArguments {

    String[] arguments;

    @Override
    public Pack execute(Pack pack) {
        pack.pack("Скрипт выполнен\n");

        return pack;
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
