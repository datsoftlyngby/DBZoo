package dbzoo.entries;

import dbzoo.domain.Animal;
import dbzoo.domain.AnimalRepository;
import dbzoo.infrastructure.Database;

import java.time.LocalDate;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException {
        AnimalRepository d = new Database();
        d.createAnimal(Animal.createAnimal("Peter",
                LocalDate.of(2020, 10, 2),
                Animal.AnimalType.ELEPHANT));
        for (Animal a : d.findAllAnimals()) {
            System.out.println(a);
        }
    }

}
