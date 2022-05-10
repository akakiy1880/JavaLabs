package collection;

import utils.HumanBeingFieldReader;

import java.time.LocalDateTime;

public class CollectionHumanBeing {
    private long id = 1;

    HumanBeingFieldReader HumanBeingFieldsReader = new HumanBeingFieldReader();


    public HumanBeing createHumanBeing() {
        return new HumanBeing(getId(),
                HumanBeingFieldsReader.readName(),
                HumanBeingFieldsReader.readCoordinates(),
                LocalDateTime.now(),
                HumanBeingFieldsReader.readRealHero(),
                HumanBeingFieldsReader.readHasToothpick(),
                HumanBeingFieldsReader.readImpactSpeed(),
                HumanBeingFieldsReader.readWeaponType(),
                HumanBeingFieldsReader.readMood(),
                HumanBeingFieldsReader.readCar());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id++;
    }
}
