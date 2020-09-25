package dbzoo.domain.animal;

public interface AnimalRepository extends AnimalFactory {
    Iterable<Animal> findAllAnimals();
    AnimalType findTypeOfAnimal(Animal animal);
    AnimalType findAnimalType(String name);
}
