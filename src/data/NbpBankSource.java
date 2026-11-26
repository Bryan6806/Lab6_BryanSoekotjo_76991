package data;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;

public class NbpBankSource {
    private static final String NBP_FILE_URL =
            "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

    public String download() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(NBP_FILE_URL))
                .GET()
                .build();

        HttpResponse<byte[]> response =
                client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new IOException("Server returned status " + response.statusCode());
        }

        // The NBP file uses the CP852 encoding (Polish letters)
        return new String(response.body(), Charset.forName("CP852"));
    }
}