package textfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadTextfileDemo {
	public static void main(String[] args) {
		readLines("Textfile1.txt", "UTF-8");
		readChunks("Textfile1.txt", "UTF-8");
	}

	static void readChunks(String filename, String encoding) {
		// TODO: das File zum Lesen öffnen, mit der angegebenen Kodierung
		try  {
			char [] buffer = new char[16]; // Sehr klein, damit die Schleife mehrmals ausgeführt wird
			int count;
			// Speicherplatz für das Gelesene
			StringBuilder content = new StringBuilder();
			// TODO: Lesen
			
			// alles gelesen -> Ausgeben
			System.out.println("Vom File gelesen: ");
			System.out.println(content.toString());
			
		} catch (Exception e) {
			System.out.println("Fehler beim Einlesen: " + e);
		}
	}

	static void readLines(String filename, String encoding) {
		try {
			String line;
			StringBuilder content = new StringBuilder();

			// TODO Lesen

			System.out.println("Gelesen: " );
			System.out.println(content.toString());
		} catch (Exception e) {
			System.out.println("Fehler beim Einlesen: " + e);
		}
	}

}
