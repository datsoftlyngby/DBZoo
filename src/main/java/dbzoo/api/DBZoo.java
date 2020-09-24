package dbzoo.api;

import dbzoo.domain.animal.Animal;
import dbzoo.domain.animal.AnimalRepository;

public class DBZoo {
    private final AnimalRepository animals;

    public DBZoo(AnimalRepository animals) {
        this.animals = animals;
    }


    public Iterable<Animal> findAllAnimals() {
        return animals.findAllAnimals();
    }
}
