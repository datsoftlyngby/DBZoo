package dbzoo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Animal {
    private final int id;
    private final String name;
    private final LocalDate birthday;
    private final LocalDateTime lastFed;
    private final AnimalType type;

    public Animal(int id, String name, LocalDate birthday, LocalDateTime lastFed, AnimalType type) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.lastFed = lastFed;
        this.type = type;
    }

    public static Animal createAnimal(String name, LocalDate birthday, AnimalType type) {
        return new Animal(-1, name, birthday, LocalDateTime.now(), type);
    }

    public Animal withId(int id) {
        return new Animal(id, this.name, birthday, lastFed, this.type);
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
                ", birthday=" + birthday +
                ", lastFed=" + lastFed +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDateTime getLastFed() {
        return lastFed;
    }

    public int getId() {
        return id;
    }

    public AnimalType getType() {
        return type;
    }
}
