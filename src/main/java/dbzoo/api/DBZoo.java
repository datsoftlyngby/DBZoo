package dbzoo.api;

import java.util.ArrayList;
import java.util.List;

public class DBZoo {
    private final List<MessageObserver> observers;

    public DBZoo() {
        this.observers = new ArrayList<MessageObserver>();
    }

    public void register(MessageObserver observer) {
        observers.add(observer);
    }

    public void sendMessage(String message) {
        for (MessageObserver mo : observers) {
            mo.notifyNewMessage(message);
        }
    }

    public static interface MessageObserver {
        void notifyNewMessage(String message);
    }
}
