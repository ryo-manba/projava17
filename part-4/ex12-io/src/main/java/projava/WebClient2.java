package projava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebClient2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("https://example.com/");
        HttpRequest req = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        body.lines()
            .limit(5)
            .forEach(System.out::println);
    }
}
