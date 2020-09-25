package dbzoo.api;

import dbzoo.domain.animal.Animal;
import dbzoo.domain.animal.AnimalRepository;
import dbzoo.domain.animal.AnimalType;

import java.time.LocalDate;

public class DBZoo {
    private final AnimalRepository animals;

    public DBZoo(AnimalRepository animals) {
        this.animals = animals;
    }

    public Iterable<Animal> findAllAnimals() {
        return animals.findAllAnimals();
    }

    public AnimalType findTypeOfAnimal(Animal animal) {
        return animals.findTypeOfAnimal(animal);
    }

    public Animal createAnimal(String name, LocalDate now, AnimalType animalType) {
        return animals.createAnimal(name, now, animalType);
    }

    public AnimalType findAnimalType(String type) {
        return animals.findAnimalType(type);
    }
}
