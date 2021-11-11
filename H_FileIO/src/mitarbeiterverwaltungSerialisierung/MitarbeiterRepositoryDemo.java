package mitarbeiterverwaltungSerialisierung;

import serialization.model.Auto;
import serialization.model.Fahrrad;
import serialization.model.LKW;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class MitarbeiterRepositoryDemo {

    private static String fileName = "maSerialze";
    private static MitarbeiterRepository maRepository = new MitarbeiterRepository(fileName);
    
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
/*                fzVerwaltung.loadData();
                fzVerwaltung.showAll();*/
            }

        } catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("Es ist ein Fehler aufgetreten:");
            e.printStackTrace();
        }

    }

    private static void initalSaveData() throws IOException {
/*        maRepository.add(new Auto("Mercedes", 30000, LocalDate.of(2019, 12, 15), 110, "Silber"));
        maRepository.add(new LKW("MAN", 150000, LocalDate.of(2020, 10, 2), 320, "Rot", 8000, 100, 3));
        maRepository.add(new Fahrrad("KTM", 2000, LocalDate.of(2020, 5, 21), 27, "Mountainbike"));

        System.out.println("Folgende Fahrzeuge werden gespeichert");
        maRepository.showAll();
        maRepository.saveData();*/


        Mitarbeiter mitarbeiter = new Mitarbeiter("Georg Angestellter", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "c#");
        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Mitarbeiter manager1 = new Manager("Franz I Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Mitarbeiter manager2 = new Manager("Franz II Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 2000);

        maRepository.maHinzufuegen(mitarbeiter);
        maRepository.maHinzufuegen(experte1);
        maRepository.maHinzufuegen(experte2);
        maRepository.maHinzufuegen(manager1);
        maRepository.maHinzufuegen(manager2);


    }
}
