package serialization_mit_bom;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import serialization_mit_bom.model.*;

public class ObjectIODemo {
    private static Scanner scanner = new Scanner(System.in);

    private static String fileName = "Fahrzeuge2.bin";
    private static FahrzeugVerwaltung fzVerwaltung = new FahrzeugVerwaltung(fileName);

    public static void main(String[] args) {
        // gespeicherte Daten einlesen
        try {

            File fzFile = new File(fileName);
            // File nicht vorhanden?
            if (!fzFile.exists()) {
                // Erst-speicherung
                initalSaveData();
            } else {
                // Sonst: Laden, anzeigen, usw...
                fzVerwaltung.loadData();
                fzVerwaltung.showAll();
                // Porsche löschen, wenn vorhanden
                boolean removed = fzVerwaltung.remove("Porsche");
                if (removed) {
                    System.out.println("Porsche entfernt");
                } else {
                    // neu hinzufügen, wenn nicht vorhanden
                    fzVerwaltung.add(new Auto("Porsche", 50000, LocalDate.now(), 300, "Schwarz"));
                    System.out.println("Porsche hinzugefügt");
                }
                System.out.println("Folgende Fahrzeuge werden gespeichert ");
                fzVerwaltung.showAll();
                fzVerwaltung.saveData();
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Es ist ein Fehler aufgetreten:");
            e.printStackTrace();
        }

    }

    private static void initalSaveData() throws IOException {
        fzVerwaltung.add(new Auto("Mercedes", 30000, LocalDate.of(2019, 12, 15), 110, "Silber"));
        fzVerwaltung.add(new LKW("MAN", 150000, LocalDate.of(2020, 10, 2), 320, "Rot", 8000, 100, 3));
        fzVerwaltung.add(new Fahrrad("KTM", 2000, LocalDate.of(2020, 5, 21), 27, "Mountainbike"));

        System.out.println("Folgende Fahrzeuge werden gespeichert");
        fzVerwaltung.showAll();
        fzVerwaltung.saveData();

    }
}
