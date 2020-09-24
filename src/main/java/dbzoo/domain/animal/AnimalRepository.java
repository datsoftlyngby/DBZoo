package dbzoo.domain.animal;

public interface AnimalRepository extends AnimalFactory {
    Iterable<Animal> findAllAnimals();
}
