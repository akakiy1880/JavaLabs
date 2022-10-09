package server;

import dragon.Color;
import dragon.Country;
import dragon.Dragon;
import dragon.DragonCharacter;
import packer.Pack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class CollectionInfo {
    Response response = new Response();
    ByteBuffer buffer = ByteBuffer.allocate(32768);

    public String getInfoAboutCollection(CollectionManager collectionManager) {
        String str = "";
        str += "Дата создания коллекции " + collectionManager.getCreationCollectionDate() + "\n";
        str += "Количество элементов в коллеции " + collectionManager.getDragons().size();
        return str;
    }

    public String show(CollectionManager collectionManager, SocketChannel client) {

        String str = "";
        if (collectionManager.getDragons().size() == 0) {
            str += "Коллекция пуста!\n";
        } else {
            for (Dragon vals : collectionManager.getDragons()) {
                str += "\nDragon(id=" + vals.getId() + "): ";
                str += "\ndragon name=" + vals.getName() + ", ";
                str += "\ndragon coordinate X=" + vals.getCoordinates().getX() + ", ";
                str += "\ndragon coordinate Y=" + vals.getCoordinates().getY() + ", ";
                str += "\ncreation date=" + vals.getCreationDate() + ", ";
                str += "\ndragon age=" + vals.getAge() + ", ";
                str += "\ndragon weight=" + vals.getWeight() + ", ";
                str += "\nspeakable=" + vals.getSpeaking() + ", ";
                str += "\ncharacter=" + vals.getCharacter() + ", ";
                str += "\nkiller name=" + vals.getKiller().getName() + ", ";
                str += "\nkiller passport=" + vals.getKiller().getPassportID() + ", ";
                str += "\nkiller hair color=" + vals.getKiller().getHairColor() + ", ";
                str += "\nkiller country=" + vals.getKiller().getNationality() + ", ";
                str += "\nkiller coordinate X=" + vals.getKiller().getLocation().getX() + ", ";
                str += "\nkiller coordinate Y=" + vals.getKiller().getLocation().getY() + ", ";
                str += "\nkiller place name=" + vals.getKiller().getLocation().getName() + "\n";
            }
        }
        return str;
    }

    public String getFieldsName() {
        return "Список всех полей:\nname(String)\ncoordinate_x(Integer)\ncoordinate_y(Double)\nage(Integer)\nweight\nspeaking\ncharacter: " +
                Arrays.toString(DragonCharacter.values()) + "killer\npassport\ncolor_heir: " + Arrays.toString(Color.values()) +
                "nationality: " + Arrays.toString(Country.values()) + "location_X\nlocation_Y\nlocation";
    }
}
