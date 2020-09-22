package dbzoo.domain;

public interface AnimalRepository {
    Iterable<Animal> findAllAnimals();
    Animal createAnimal(Animal animal);
}
