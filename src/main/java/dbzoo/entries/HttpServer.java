package dbzoo.entries;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("[SERVER] Started server at: " + serverSocket);

            Socket socket = serverSocket.accept();
            System.out.println("[SERVER] Connected to: " + socket);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String line;
            while(!(line = reader.readLine()).isEmpty()) {
                System.out.println(line);
            }

            StringWriter sw = new StringWriter();
            PrintWriter w = new PrintWriter(sw);
            w.println("<html>");
            w.println("<body>");
            w.println("<h1>Hello, World!</h1>");
            w.println("</body>");
            w.println("</html>");

            String msg = sw.toString();
            byte [] content = msg.getBytes(StandardCharsets.UTF_8);

            String response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html; charset=utf-8\r\n"
                            + "Content-Length: " + content.length + "\r\n"
                            + "\r\n";

            var out = socket.getOutputStream();
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.write(content);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
