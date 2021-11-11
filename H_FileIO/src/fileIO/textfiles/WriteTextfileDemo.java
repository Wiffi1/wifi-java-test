package fileIO.textfiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

	static void writeFile(String fileName, String encoding) {

		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

		// TODO schreiben
		try {

			String line;
			// Solange eine nicht-leere Zeichenfolge eingegeben wurde
			while ((line = input.nextLine()) != null && !line.isEmpty()) {

			}

		} catch (Exception e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

}
