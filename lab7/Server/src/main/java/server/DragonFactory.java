package server;

import dragon.Dragon;
import utils.DragonFieldsReader;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DragonFactory implements Serializable {
    private int id = 1;
    DragonFieldsReader dragonFieldsReader = new DragonFieldsReader();

    public Dragon createDragon() {
        return new Dragon(getId(),
                dragonFieldsReader.readName(),
                dragonFieldsReader.readCoordinates(),
                Date.valueOf(LocalDate.now()),
                dragonFieldsReader.readAge(),
                dragonFieldsReader.readWeight(),
                dragonFieldsReader.readSpeaking(),
                dragonFieldsReader.readCharacter(),
                dragonFieldsReader.readKiller());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id++;
    }
}