package dbzoo.entries;

import dbzoo.domain.Animal;
import dbzoo.domain.AnimalRepository;
import dbzoo.infrastructure.Database;

public class Server {

    public static void main(String[] args) throws ClassNotFoundException {
        AnimalRepository d = new Database();
        Animal before = Animal.createAnimal("Big Gray", Animal.AnimalType.ELEPHANT);
        Animal after = d.createAnimal(before);
        System.out.println("before: " + before + " after: " + after);
        System.out.println(d.findAllAnimals());
    }

}
