package interfaces;

import packer.Pack;

public interface Command {
    public Pack execute(String nameCommand, Pack pack);
}
