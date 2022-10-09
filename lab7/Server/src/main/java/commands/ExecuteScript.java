package commands;


import interfaces.CommandWithArguments;
import packer.Pack;

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
