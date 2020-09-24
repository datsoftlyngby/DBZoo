package dbzoo.ui;

import dbzoo.api.DBZoo;

public class Protocol implements DBZoo.MessageObserver {
    private final DBZoo zoo;

    public Protocol(DBZoo zoo) {
        this.zoo = zoo;
    }

    public void run () {
        zoo.register(this);
        zoo.register(this);
        zoo.register(this);

        String msg = "hello";
        zoo.sendMessage(msg);
    }

    public static void main(String[] args) {
        new Protocol(new DBZoo()).run();
    }

    @Override
    public void notifyNewMessage(String message) {
        System.out.println(message);
    }
}
