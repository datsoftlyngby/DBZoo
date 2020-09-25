package dbzoo.infrastructure;

import dbzoo.domain.animal.Animal;
import dbzoo.domain.animal.AnimalRepository;
import dbzoo.domain.animal.AnimalType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database implements AnimalRepository {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/dbzoo";

    // Database credentials
    private static final String USER = "dbzoo";

    // Database version
    private static final int version = 3;

    public Database() {
        if (getCurrentVersion() != getVersion()) {
            throw new IllegalStateException("Database in wrong state, expected:"
                    + getVersion() + ", got: " + getCurrentVersion());
        }
    }

    public Iterable<Animal> findAllAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, birthday, type FROM animals;");
            while (rs.next()) {
                Animal a = new Animal(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate()
                        );
                animals.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return animals;
        }
        return animals;
    }

    @Override
    public AnimalType findTypeOfAnimal(Animal animal) {
        try (var conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT at.id, at.name, at.required_space " +
                            "FROM animal_types AS at JOIN animals " +
                            "ON animals.type=at.id " +
                            "WHERE animals.id=?;"
            );
            ps.setInt(1, animal.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AnimalType(
                        rs.getInt("at.id"),
                        rs.getString("at.name"),
                        rs.getInt("at.required_space")
                );
            } else {
                throw new IllegalStateException();
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public AnimalType findAnimalType(String name) {
        try (var conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT at.id, at.name, at.required_space " +
                            "FROM animal_types AS at " +
                            "WHERE at.name = ?;"
            );
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AnimalType(
                        rs.getInt("at.id"),
                        rs.getString("at.name"),
                        rs.getInt("at.required_space")
                );
            } else {
                throw new IllegalStateException();
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Animal createAnimal(String name, LocalDate birthday, AnimalType type) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO animals (name, birthday, type) VALUES (?,?,?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, name);
            ps.setDate(2, java.sql.Date.valueOf(birthday));
            ps.setInt(3, type.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return new Animal(rs.getInt(1), name, birthday);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getVersion() {
        return version;
    }

    public static int getCurrentVersion() {
        try (Connection conn = getConnection()) {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'version';");
            if(rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            } else {
                System.err.println("No version in properties.");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, null);
    }
}
