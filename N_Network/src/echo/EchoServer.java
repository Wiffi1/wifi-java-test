package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Server wird gestartet");

		try {
			ServerSocket server = new ServerSocket(4321);
			boolean keepRunning = true;
			while (keepRunning) {
				System.out.println("Warte auf Clients...");
				// Client-Verbindung annehmen (blockiert, bis sich ein Client gefunden hat)
				Socket client = server.accept();
				// Info zum Client anzeigen
				System.out.println("Client hat sich verbunden: " + client.getInetAddress());
				try {
					// Reader und Writer
					// Daten mit dem Client austauschen
					BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
					// Daten mit dem Client austauschen (Reihenfolge muss mit dem Client zusammenpassen)
					String line;
					// So lange der Client Daten sendet (readline liefert null, wenn EOF erreicht ist;
					// EOF wird vom Reader gesendet, wenn der Client die Verbindung schließt)
					while ((line = reader.readLine()) != null) {
						System.out.println("Vom Client empfangen: " + line);
						// die Daten zurückschicken
						writer.write("echo: " + line);
						writer.newLine();
						// Das Senden der Daten jetzt erzwingen
						writer.flush();
					}
				} catch (Exception e) {
					System.err.println("Fehler in Client-Kommunikation");
					e.printStackTrace();
				}
				System.out.println("Der Client hat keine weiteren Daten gesendet");
				// alle Ressourcen für den Socket freigeben
				client.close();
			}
			// wenn die Serverschleife zu Ende ist, auch die serverseitigen Ressourcen freigeben.
			server.close();
		} catch (Exception e) {
			System.err.println("Fehler im EchoServer:");
			e.printStackTrace();
		}

	}

}
