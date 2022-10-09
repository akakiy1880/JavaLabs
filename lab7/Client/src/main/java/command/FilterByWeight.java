package command;

import interfaces.CommandWithArgument;
import packer.Pack;

public class FilterByWeight implements CommandWithArgument {
    private String[] arg;

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        pack.pack(nameCommand, arg);
        return pack;
    }

    @Override
    public void arg(String[] arg) {
        this.arg = arg;
    }
}
