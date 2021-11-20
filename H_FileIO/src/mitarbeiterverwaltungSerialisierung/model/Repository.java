package mitarbeiterverwaltungSerialisierung.model;

import mitarbeiterverwaltungSerialisierung.OrderBy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 2L;
    private List<Mitarbeiter> list = new ArrayList<>();
    private String fileName;

    /*    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();
    private MitarbeiterVerwaltung maVerwaltung = new MitarbeiterVerwaltung();
    private List<String> stringListe = new ArrayList<>();
   */

    public Repository(String fileName) {
//        this.mitarbeiterListe = mitarbeiterListe;
        this.fileName = fileName;
        System.out.println(this.fileName);
        try {
            File maFile = new File(fileName);
            // File nicht vorhanden?
            if (!maFile.exists()) {
                // Erst-speicherung
/*                initalSaveData();
                saveData();*/
            } else {
                // Sonst: Laden, anzeigen, usw...
                loadData();
            }
        } catch (ClassNotFoundException | IndexOutOfBoundsException | IOException e) {
//        } catch (IndexOutOfBoundsException e) {
            System.out.println("Es ist ein Fehler aufgetreten:" + e);
            e.printStackTrace();
        }
    }

    public void saveData() throws IOException {
        // Speichern
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            // Die Liste "serialisieren", d.h. alle Objekte mit ihren Attributen
            // in den Stream speichern (Das Ergebnis ist ein Binär-File)

//            oos.writeObject(list);
//            oos.writeObject(maVerwaltung);
//            oos.writeObject(list);
//            oos.writeObject(mitarbeiterListe);
//            oos.writeObject(stringListe);

//            https://www.tutorialspoint.com/can-we-serialize-static-variables-in-java

            System.out.println("load'Data");
//            System.out.printf("\ngespeicherte Liste%s", list);

            System.out.printf("%d In File gespeichert\n",  list.size() );
            for (Mitarbeiter listItem : list) {
                System.out.printf("\t%s\n", listItem);
            }

            oos.writeObject(list);
            oos.writeInt(Mitarbeiter.getNextId());
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
            list = temp;
            Mitarbeiter.initNextId(ois.readInt());
            System.out.printf("%d Mitarbeiter vom File geladen\n",  list.size() );
            System.out.printf("\n %d Mitarbeiter vom File geladen\n",  temp.size() );
            for (Mitarbeiter listItem : list) {
                System.out.printf("\t%s\n", listItem);
            }
        }
    }

    public List<Mitarbeiter> getAll() throws IOException, ClassNotFoundException {
        loadData();
        return list;
    }

    public Mitarbeiter get(int index) {
        Mitarbeiter ma = list.get(index);
        return ma;
    }

    public Mitarbeiter update(int index, Mitarbeiter ma) throws IOException {
        Mitarbeiter tmpMa = list.get(index);
        // ich glaube, das funzt so noch nicht.
        tmpMa = ma;
        saveData();
        return ma;
    }

    public void add(Mitarbeiter ma) throws IOException {
        list.add(ma);
        saveData();
    }

    public void remove(int index) throws IOException {
        list.remove(index);
        saveData();
    }

}




/*
    private void initalSaveData() {
        Mitarbeiter mitarbeiter = new Mitarbeiter("Georg Angestellter", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "c#");
        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Mitarbeiter manager1 = new Manager("Franz I Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Mitarbeiter manager2 = new Manager("Franz II Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 2000);

        maVerwaltung.maHinzufuegen(mitarbeiter);
        maVerwaltung.maHinzufuegen(experte1);
        maVerwaltung.maHinzufuegen(experte2);
        maVerwaltung.maHinzufuegen(manager1);
        maVerwaltung.maHinzufuegen(manager2);

        mitarbeiterListe.add(mitarbeiter);
        mitarbeiterListe.add(experte1);
        mitarbeiterListe.add(experte2);
        mitarbeiterListe.add(manager1);
        mitarbeiterListe.add(manager2);

        stringListe.add("ersterString");
    }*/


/*
*     public void maHinzufuegen(Mitarbeiter ersatzExperte) {
        maVerwaltung.maHinzufuegen(ersatzExperte);
        try {
            saveData();
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler beim Hinzufügen eines Mitarbeiters --");
        }
        System.out.printf("\tfolgender Esperte wurde eingstellt: \t%s\n", ersatzExperte);
    }

    public Mitarbeiter maAusscheiden(int mitarbeiterID) {
        Mitarbeiter ma = maVerwaltung.maAusscheiden(mitarbeiterID);
        try {
            saveData();
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler beim Hinzufügen eines Mitarbeiters --");
        }
        return ma;
    }

    public void alleAnzeigen(OrderBy orderBy) {
        maVerwaltung.alleAnzeigen(orderBy);
    }
* */