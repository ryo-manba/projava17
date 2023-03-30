package projava;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        var soc = new Socket("localhost", 1600);
        OutputStream output = soc.getOutputStream();
        output.write(234);
        output.close();
        soc.close();
    }
}
