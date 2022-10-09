package command;

import interfaces.Command;
import packer.Pack;
import util.DragonReader;

public class Add implements Command {
    @Override
    public Pack execute(String nameCommand, Pack pack) {
        DragonReader dragonReader = new DragonReader();
        pack.pack(nameCommand, dragonReader.createDragon());
        return pack;
    }
}
