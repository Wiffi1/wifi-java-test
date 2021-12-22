package httpRequests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpRequestsDemo {
    public static void main(String[] args) {
        loadUrl("https://docs.oracle.com/en/java/javase/17/docs/api/", "java-doc.html");
        loadUrl("http://openjdk.java.net/images/openjdk.png", "openjdk.png");

        loadText("http://openjdk.java.net");
    }

    static void loadText(String url) {
		System.out.println("**************************** loadText *****************************");
        System.out.println("Lade URL " + url);

        try {
            URI uri = new URI(url);
            // request erzeugen
            HttpRequest req = HttpRequest.newBuilder(uri).GET().setHeader("User-Agent", "WIFI Java SW Dev").build();

            System.out.println("Request headers:");
            req.headers().map().forEach((hKey, hValue) -> {
                System.out.printf("\t%s: %s %n", hKey, hValue);
            });

            // Handler für den Body erzeugen
            BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
            // client erzeugen
            HttpClient client = HttpClient.newHttpClient();

            // request senden
            HttpResponse<String> response = client.send(req, bodyHandler);
            // auf das ergebnis zugreifen:
            int statusCode = response.statusCode();
            System.out.println("HTTP status code: " + statusCode);
            System.out.println("Response headers:");
            response.headers().map().forEach((hKey, hValue) -> {
                System.out.printf("\t%s: %s %n", hKey, hValue);
            });
            if (statusCode < 400) {

                String body = response.body();
                System.out.println("Ressource geladen, Länge=" + body.length());
                System.out.println(response.body().substring(0, body.length() < 500 ? body.length() : 500));
            } else {
                System.out.println("Die Ressource konnte nicht geladen werden. Fehlerinformation:");
                System.out.println(response.body());
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void loadUrl(String url, String dest) {
		System.out.println("**************************** loadUrl ******************************");
		System.out.println("Lade URL " + url);

        try {
            URI uri = new URI(url);
            // request erzeugen
            HttpRequest req = HttpRequest.newBuilder(uri).GET().setHeader("User-Agent", "WIFI Java SW Dev").build();
            System.out.println("Request headers:");
            req.headers().map().forEach((hKey, hValue) -> {
                System.out.printf("\t%s: %s %n", hKey, hValue);
            });
            // Handler für den Body erzeugen, der Handler wird den Response in das angegebene File speicher.
            BodyHandler<Path> bodyHandler = HttpResponse.BodyHandlers.ofFile(Paths.get(dest));
            // client erzeugen
            HttpClient client = HttpClient.newHttpClient();

            // request senden
            HttpResponse<Path> response = client.send(req, bodyHandler);
            // auf das ergebnis zugreifen:
            int statusCode = response.statusCode();
            System.out.println("HTTP status code: " + statusCode);
            System.out.println("Response headers:");
            response.headers().map().forEach((hKey, hValue) -> {
                System.out.printf("\t%s: %s %n", hKey, hValue);
            });
            if (statusCode < 400) {
                System.out.println("Ressource erfolgreich geladen");
                // nichts weiter zu tun, der Handler hat den Response ins 'File geschrieben
            } else {
                // Files.move(Paths.get(dest), Paths.get(dest + ".txt"));
                System.out.println("Die Ressource konnte nicht geladen werden. " +
						"Das gespeicherte File enthält möglicherweise weitere Fehlerinformationen");
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
