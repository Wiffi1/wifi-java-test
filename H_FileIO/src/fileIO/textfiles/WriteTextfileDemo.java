package fileIO.textfiles;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/*
 Wenn ein Java Programm unter Windows im Command-Fenster ausgeführt wird,
 sollte die Codepage angepasst werden, damit die Zeichenfolgen-Codierung funktioniert:
 chcp 1252 
 */

public class WriteTextfileDemo {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		writeFile("Textfile1.txt", "UTF-8");
	}

	static void writeFileOld(String fileName, String encoding) {

		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

		// schreiben
		// Der Filewriter verwendet standardmäßid das Encoding der VMm unter Windows ist das AMSI (CP 1252)
		// Mit dem Charset (Charset.forName(encoding)) legen wrs das Encoding selber fest
		// mit try-with-resources wird der Stream automatisch geschlossen.
		try {

			BufferedWriter writer = new BufferedWriter(
					new FileWriter(fileName, Charset.forName(encoding)));
			String line;
			// Solange eine nicht-leere Zeichenfolge eingegeben wurde
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				writer.newLine();
				writer.write(line);
			}
			// File schließen ist jetz nicht mehr nötig, das Schließen wird jetzt im finallyBlock gemacht.
//			writer.close();

		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

	static void writeFile(String fileName, String encoding) {

		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

		// schreiben
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(fileName, Charset.forName(encoding)))) {

			String line;
			// Solange eine nicht-leere Zeichenfolge eingegeben wurde
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				writer.newLine();
				writer.write(line);
			}
			// File schließen (der BufferedWriter shließt den FilewWerier der FilWeerter schließt das File
			// das müssten wir eicentlich im finally -block machen -> besser mit try-with-resources lösen.
//			writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

}
