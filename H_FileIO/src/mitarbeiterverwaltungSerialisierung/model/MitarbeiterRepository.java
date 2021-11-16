package mitarbeiterverwaltungSerialisierung.model;

import mitarbeiterverwaltungSerialisierung.model.Experte;
import mitarbeiterverwaltungSerialisierung.model.Manager;
import mitarbeiterverwaltungSerialisierung.model.Mitarbeiter;
import mitarbeiterverwaltungSerialisierung.model.MitarbeiterVerwaltung;
import serialization.model.Fahrzeug;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterRepository {

    // Version von unserem Dokument-Format
//    private static final long serialVersionUID = 2L;
//    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();
    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();
    private MitarbeiterVerwaltung maVerwaltung = new MitarbeiterVerwaltung();
    private List<String> stringListe = new ArrayList<>();

    private String fileName;

    public MitarbeiterRepository(String fileName) {
//        this.mitarbeiterListe = mitarbeiterListe;
        this.fileName = fileName;
        System.out.println(this.fileName);
        try {
            File maFile = new File(fileName);
            // File nicht vorhanden?
            if (!maFile.exists() || true) {
                // Erst-speicherung
                initalSaveData();
                saveData();
            } else {
                // Sonst: Laden, anzeigen, usw...
                loadData();
/*                maFile.loadData();
                maFile.showAll();*/
            }

        } catch (ClassNotFoundException | IndexOutOfBoundsException | IOException e) {
//        } catch (IndexOutOfBoundsException e) {
            System.out.println("Es ist ein Fehler aufgetreten:");
            e.printStackTrace();
        }
    }

    private void initalSaveData() {
        Mitarbeiter mitarbeiter = new Mitarbeiter("Georg Angestellter", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "c#");
        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Mitarbeiter manager1 = new Manager("Franz I Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Mitarbeiter manager2 = new Manager("Franz II Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 2000);

/*        maVerwaltung.maHinzufuegen(mitarbeiter);
        maVerwaltung.maHinzufuegen(experte1);
        maVerwaltung.maHinzufuegen(experte2);
        maVerwaltung.maHinzufuegen(manager1);
        maVerwaltung.maHinzufuegen(manager2);*/

        mitarbeiterListe.add(mitarbeiter);
        stringListe.add("ersterString");
    }

    public void saveData() throws IOException {
        // Speichern
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            // Die Fahrzeug-Liste "serialisieren", d.h. alle Objekte mit ihren Attributen
            // in den Stream speichern (Das Ergebnis ist ein Binär-File)
//            oos.writeObject(maVerwaltung.mitarbeiterListe);

//            oos.writeObject(maVerwaltung);

//            oos.writeObject(maVerwaltung.mitarbeiterListe);

//            oos.writeObject(mitarbeiterListe);

            oos.writeObject(stringListe);


            // den Zähler für die Fahrzeugnummer auch speichern
//            oos.writeInt(mitarbeiterListe.getNextNr());
        }
    }

    public void loadData() throws ClassNotFoundException, IOException {
        // laden
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            // die Fahrzeuge "deserialisieren", dh aus dem Stream die Array-List und alle
            // Fahrzeugobjekte wiederherstellen
            // Ergebnis ist vom Typ Object -> casten auf den erwarteten Typ
            @SuppressWarnings("unchecked")
            List<Mitarbeiter> temp = (List<Mitarbeiter>) ois.readObject();
//            mitarbeiterListe = temp;
//            Fahrzeug.initNextNr(ois.readInt());
//            System.out.printf("%d Fahrzeuge vom File geladen\n",  fahrzeuge.size() );
            System.out.printf("%d Mitarbeiter vom File geladen\n",  temp.size() );
        }
    }



}
