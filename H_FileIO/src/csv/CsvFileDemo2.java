package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvFileDemo2 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Daten speichern? j/n");
		boolean saveData = scanner.nextLine().charAt(0) == 'j';
		boolean readData = saveData ? saveCsv("autos1.csv") : true;

		if (saveData) {
			System.out.println("Weiter mit Return");
			scanner.nextLine();
		}
		if (readData) {
			loadCsv("autos1.csv");
		}

	}

	static boolean saveCsv(String fileName) {

		Auto[] data = { new Auto("Mercedes", 29999.99, LocalDate.of(2020, 12, 15), 130, "Silber"),
				new Auto("Audi", 26599.99, LocalDate.of(2020, 10, 2), 120, "Rot"),
				new Auto("Opel", 20999.99, LocalDate.of(2020, 5, 21), 90, "Weiß") };
		System.out.println("Folgende Daten werden gespeichert:");
		for (Auto auto : data) {
			System.out.println(auto);
		}

		// Daten speichern
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, Charset.forName("UTF-8")))) {

            for (Auto item : data) {
                // für CSV müssen selber die Reihenfolge definineren
				writer.printf("%d,%s;%.2f;%s;%d;%s\n",
						item.getNr(), item.getMarke(), item.getPreis(),
						item.getErzeugt(), item.getLeistung(), item.getFarbe());
            }
			System.out.println("Daten gespeichert");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

    static void loadCsv(String fileName) {
        List<Auto> fahrzeuge = new ArrayList<>();
        // BufferedReader zum zeilenweisen lesen öffnen
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            // TODO solange es Zeilen zu lesen gibt
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(";");
				// Werte sind in dieser Reihennfolge im Array
//				item.getNr(), item.getMarke(), item.getPreis(),
//						item.getErzeugt(), item.getLeistung(), item.getFarbe()
				Auto item = new Auto();
			}

            System.out.println("Daten gelesen:");
            for (Auto auto : fahrzeuge) {
                System.out.println(auto);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der CSV Daten:");
            System.out.println(e.toString());
        }
    }
}
