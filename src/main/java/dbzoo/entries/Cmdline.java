package dbzoo.entries;

import dbzoo.api.DBZoo;
import dbzoo.domain.animal.Animal;
import dbzoo.domain.animal.AnimalType;
import dbzoo.infrastructure.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cmdline {
    private final DBZoo zoo;
    
    public Cmdline() {
        zoo = new DBZoo(new Database());
    }

    public void parseArgs (String[] args) {
        if ("animals".equals(args[0])) {
            if ("list".equals(args[1])) {
                listAnimals();
            } else if ("add".equals(args[1])) {
                addAnimal(args[2], args[3]);
            }
        } else {
            System.err.println("Bad command: " + args[0]);
        }
    }

    public void listAnimals() {
        for (Animal a : zoo.findAllAnimals()) {
            System.out.printf("%-15s (%s) - %s%n", a.getName(), a.getBirthday().format(DateTimeFormatter.ISO_DATE),
                    zoo.findTypeOfAnimal(a).getName()
                    );
        }

    }

    public void addAnimal(String name, String type) {
        AnimalType animalType = zoo.findAnimalType(type);
        Animal a = zoo.createAnimal(name, LocalDate.now(), animalType);
        System.out.println(a);
    }

    public static void main(String[] args) {
        new Cmdline().parseArgs(args);
    }
    

}
