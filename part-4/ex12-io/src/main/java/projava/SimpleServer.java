package projava;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        var server = new ServerSocket(1600);
        System.out.println("Waiting...");
        Socket soc = server.accept();
        System.out.println("connect from " + soc.getInetAddress());
        InputStream input = soc.getInputStream();
        System.out.println(input.read());
        input.close();
        soc.close();
    }
}
