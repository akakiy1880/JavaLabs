package interfaces;

import packer.Pack;

public interface Command {
    Pack execute(Pack pack);

    String getDescription();
}
