package dbzoo.infrastructure;

import dbzoo.domain.Animal;
import dbzoo.domain.AnimalRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements AnimalRepository {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/dbzoo";

    //  Database credentials
    private static final String USER = "dbzoo";

    public Database() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
    }

    public Iterable<Animal> findAllAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, null)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, type FROM animals;");
            while (rs.next()) {
                Animal a = new Animal(rs.getInt("id"),
                        rs.getString("name"),
                        Animal.AnimalType.values()[rs.getInt("type")]);
                animals.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return animals;
        }
        return animals;
    }

    @Override
    public Animal createAnimal(Animal animal) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, null)) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO animals (name, type) VALUES (?,?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, animal.getName());
            ps.setInt(2, animal.getType().ordinal());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return animal.withId(rs.getInt(1));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
