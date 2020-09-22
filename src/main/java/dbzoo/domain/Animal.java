package dbzoo.domain;

public class Animal {
    private final int id;
    private final String name;
    private final AnimalType type;

    public Animal(int id, String name, AnimalType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static Animal createAnimal(String name, AnimalType type) {
        return new Animal(-1, name, type);
    }

    public Animal withId(int id) {
        return new Animal(id, this.name, this.type);
    }

    public static enum AnimalType {
        TIGER,
        ELEPHANT
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public AnimalType getType() {
        return type;
    }
}
