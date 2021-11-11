package fileIO.csv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvFileDemo {

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

		// TODO: Daten speichern
		try {

            for (Auto item : data) {
                // für CSV müssen selber die Reihenfolge definineren
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
        try{
            String line;
            // TODO solange es Zeilen zu lesen gibt

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
