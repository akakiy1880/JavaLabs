package collection;

public class Car {
    private String name; //Поле не может быть null

    public Car(String name) {
        this.name = name;
    }

    public String toString() {
        return "name: " + name;
    }

    public String getName() {return name;}

    public void setCarName(String name){this.name = name;}

}
