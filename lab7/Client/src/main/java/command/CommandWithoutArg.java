package command;


import interfaces.Command;
import packer.Pack;

public class CommandWithoutArg implements Command {

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        pack.pack(nameCommand);
        return pack;
    }
}
