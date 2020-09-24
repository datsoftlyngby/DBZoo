package dbzoo.domain.animal;

import java.time.LocalDate;

public interface AnimalFactory {
    Animal createAnimal(String name, LocalDate birthday, AnimalType type);
}
