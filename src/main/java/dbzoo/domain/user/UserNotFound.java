package dbzoo.domain.user;

public class UserNotFound extends Exception {
    public UserNotFound(String name) {
        super("No User with name: " + name);
    }
}
