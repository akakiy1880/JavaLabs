package collection;

import java.time.LocalDateTime;

public class HumanBeing {
    private static long ID = 0;
    private long id = ++ID; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Integer impactSpeed; //Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле не может быть null

    public HumanBeing(long id, String name, Coordinates coordinates, LocalDateTime creationDate, boolean realHero, boolean hasToothpick,
                      Integer impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getRealHero() {
        return realHero;
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public boolean getHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public Integer getImpactSpeed() { return impactSpeed; }

    public void setImpactSpeed(Integer impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public WeaponType getWeaponType() { return weaponType; }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Mood getMood() { return mood; }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Car getCar() { return car; }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %d%n" +
                        "Название: %s%n" +
                        "Координаты: %s%n" +
                        "Дата создания: %s%n" +
                        "Настоящий герой: %b%n" +
                        "C зубочисткой в зубах: %b%n" +
                        "Cкорость удара: %s%n" +
                        "Тип оружия: %s%n" +
                        "Настроение: %s%n" +
                        "Машина: %s%n" ,
                id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed,
                weaponType, mood, car
        );
    }


}
