package projava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.SocketFactory;

public class WebClient {
    public static void main(String[] args) throws IOException {
        // domainにリクエストを投げる
        var domain = "www.google.com";

        // try (Socket soc = createHttpSocket(domain, 80);
        try (Socket soc = createHttpsSocket(domain, 443);
            var pw = new PrintWriter(soc.getOutputStream());
            var isr = new InputStreamReader(soc.getInputStream());
            var bur = new BufferedReader(isr))
        {
            pw.println("GET /index.html HTTP/1.1");
            pw.println("Host: " + domain);
            pw.println();
            pw.flush();
            bur.lines()
              .limit(18)
              .forEach(System.out::println);
        }
    }
    // http通信を行うソケットを作成する
    public static Socket createHttpSocket(String domain, int port) throws IOException {
        Socket soc = new Socket(domain, port);
        return soc;
    }
    // https通信を行うソケットを作成する
    public static Socket createHttpsSocket(String domain, int port) throws IOException {
        SocketFactory factory = SSLSocketFactory.getDefault();
        Socket soc = factory.createSocket(domain, port);
        return soc;
    }
}
