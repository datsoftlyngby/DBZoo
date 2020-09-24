package dbzoo.domain.animal;

public class AnimalType {
    private final int id;
    private final String name;
    private final int requiredSpace;

    public AnimalType(int id, String name, int requiredSpace) {
        this.id = id;
        this.name = name;
        this.requiredSpace = requiredSpace;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRequiredSpace() {
        return requiredSpace;
    }
}
