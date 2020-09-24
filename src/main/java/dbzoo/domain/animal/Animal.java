package dbzoo.domain.animal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Animal {
    private final int id;
    private final String name;
    private final LocalDate birthday;

    public Animal(int id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Animal withId(int id) {
        return new Animal(id, this.name, birthday);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

}
