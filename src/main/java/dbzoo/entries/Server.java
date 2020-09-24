package dbzoo.entries;

import dbzoo.domain.Animal;
import dbzoo.domain.AnimalRepository;
import dbzoo.infrastructure.Database;

import java.time.LocalDate;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException {
        AnimalRepository d = new Database();
        d.createAnimal(Animal.createAnimal("Young Gray",
                LocalDate.of(2020, 10, 2),
                Animal.AnimalType.ELEPHANT));
        System.out.println(d.findAllAnimals());
    }

}
