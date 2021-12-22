package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String hostName = "localhost";
		
		try {
			// Verbindung zum Server herstellen (Port muss mit Server übereinstimmen.
			Socket clientSocket = new Socket(hostName, 4321);

			// Reader und Writer erzeugen, die über der Socket lesen/schreiben
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

			System.out.println("Daten zeilenweise eingeben, beenden mit Leerzeile");
			String line;
			// solang der User Text eingeben hat
			while ((line = scanner.nextLine()) != null && !line.isEmpty()) {
				System.out.println("Sende Text: " + line);
				// Daten senden
				writer.write(line);
				// newline senden
				writer.newLine();
				// senden erzwingen
				writer.flush();
				// die Antwort lesen
				String response = reader.readLine();
				System.out.println("Antwort vom Server: " + response);
			}
			clientSocket.close();

		} catch (Exception e) {
			System.err.println("Fehler im EchoClient:");
			e.printStackTrace();
		}

	}

}
