package dbzoo.entries;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) {
        try(ServerSocket severSocket = new ServerSocket(8080)) {
            System.out.println("[SERVER] Started server at: " + severSocket);

            Socket socket = severSocket.accept();
            System.out.println("[SERVER] Connected to: " + socket);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
