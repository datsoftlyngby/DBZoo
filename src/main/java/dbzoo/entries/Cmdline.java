package dbzoo.entries;

import dbzoo.api.DBZoo;
import dbzoo.domain.animal.Animal;
import dbzoo.infrastructure.Database;

public class Cmdline {
    private final DBZoo zoo;
    
    public Cmdline() {
        zoo = new DBZoo(new Database());
    }

    public void parseArgs (String[] args) {
        if ("animals".equals(args[0])) {
            listAnimals();
        } else {
            System.err.println("Bad command: " + args[0]);
        }
    }

    public void listAnimals() {
        for (Animal a : zoo.findAllAnimals()) {
            System.out.println(a);
        }

    }

    public static void main(String[] args) {
        new Cmdline().parseArgs(args);
    }
    

}
