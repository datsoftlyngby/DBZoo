package dbzoo.entries;

import dbzoo.api.DBZoo;
import dbzoo.domain.animal.Animal;
import dbzoo.infrastructure.Database;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final DBZoo api;
    private final Socket socket;

    public Server(DBZoo api, Socket socket) {
        this.api = api;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("[CLIENT] Started");
        try {
            HttpRequest request = HttpRequest.parse(socket.getInputStream());
            System.out.println(request);

            StringWriter s = new StringWriter();
            PrintWriter p = new PrintWriter(s);
            p.println("<html>");
            p.println("<body>");
            p.println("<ul>");
            for (Animal animal : api.findAllAnimals()) {
                p.println("<li>" + animal + "</li>");
            }
            p.println("<ul>");
            p.println("<ul>");
            p.println("</body>");
            p.println("</html>");
            HttpResponse.ok("text/html", s.toString())
                    .writeTo(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(HttpRequest request) {
    }

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(4);
        DBZoo api = new DBZoo(new Database());
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("[SERVER] Started server: " + serverSocket);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("[SERVER] Accepted: " + serverSocket);
                ex.submit(new Server(api, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static class HttpRequest {
        private final String method;
        private final String address;
        private final String version;
        private final Map<String, String> headerFields;

        public HttpRequest(String method, String address,
                           String version, Map<String, String> headerFields) {
            this.method = method;
            this.address = address;
            this.version = version;
            this.headerFields = headerFields;
        }

        public static HttpRequest parse(InputStream in) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String[] words = reader.readLine().split(" ");

            String method = words[0];
            String address = words[1];
            String version = words[2];
            Map<String, String> headers = new HashMap<>();

            String line;
            while (!(line = reader.readLine()).isEmpty()) {
                String[] fields = line.split(": ");
                headers.put(fields[0], fields[1]);
            }

            return new HttpRequest(method, address, version, headers);
        }

        @Override
        public String toString() {
            return "HttpRequest{" +
                    "method='" + method + '\'' +
                    ", address='" + address + '\'' +
                    ", version='" + version + '\'' +
                    ", headerFields=" + headerFields +
                    '}';
        }
    }

    public static class HttpResponse {
        private final String version;
        private final int code;
        private final String codeString;
        private final String contentType;
        private final byte[] content;

        public static HttpResponse ok(String contentType, String content) {
            return new HttpResponse(200, "OK",
                    contentType + "; charset=utf-8",
                    content.getBytes(StandardCharsets.UTF_8));
        }

        public HttpResponse(int code, String codeString, String contentType, byte[] content) {
            this.version = "HTTP/1.1";
            this.code = code;
            this.codeString = codeString;
            this.contentType = contentType;
            this.content = content;
        }

        public void writeTo(OutputStream stream) throws IOException {
            PrintWriter writer = new PrintWriter(stream);
            writer.print(version + " " + code + " " + codeString + "\r\n");
            writer.print(codeString + "\r\n");
            writer.print("Content-Type: " + contentType + "\r\n");
            writer.print("Content-Length: " + content.length + "\r\n\r\n");
            writer.flush();
            stream.write(content);
            writer.flush();
        }

        @Override
        public String toString() {
            return "HttpResponse{" +
                    "code=" + code +
                    ", codeString='" + codeString + '\'' +
                    ", contentType='" + contentType + '\'' +
                    ", content=" + Arrays.toString(content) +
                    '}';
        }
    }
}
