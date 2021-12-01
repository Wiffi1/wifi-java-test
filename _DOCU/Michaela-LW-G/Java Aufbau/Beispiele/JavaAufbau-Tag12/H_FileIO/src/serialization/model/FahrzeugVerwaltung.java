package serialization.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FahrzeugVerwaltung {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 1L;
    private List<Fahrzeug> fahrzeuge = new ArrayList<>();

    private String fileName;

    public FahrzeugVerwaltung(String fileName) {
        this.fileName = fileName;
    }


//    public List<Fahrzeug> getFahrzeuge() {
//        return fahrzeuge;
//    }

    // ein Fahrzeug in der Liste einfügen
    public void add(Fahrzeug fz) {
        fahrzeuge.add(fz);
    }

    // das erste Fahrzeug dieser Marke entfernen
    public boolean remove(String marke) {
        for (int i = fahrzeuge.size() - 1; i >= 0; i--) {
            if (fahrzeuge.get(i).getMarke().equals(marke)) {
                fahrzeuge.remove(i);

                return true;
            }
        }
        return false;
    }

    public void showAll() {

        for (Fahrzeug fz : fahrzeuge) {
            System.out.println(fz);
        }

    }

    public void loadData() throws ClassNotFoundException, IOException {
        // laden
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            // die Fahrzeuge "deserialisieren", dh aus dem Stream die Array-List und alle
            // Fahrzeugobjekte wiederherstellen
            // Ergebnis ist vom Typ Object -> casten auf den erwarteten Typ
            @SuppressWarnings("unchecked")
            List<Fahrzeug> temp = (List<Fahrzeug>) ois.readObject();
            fahrzeuge = temp;
            Fahrzeug.initNextNr(ois.readInt());
            System.out.printf("%d Fahrzeuge vom File geladen\n",  fahrzeuge.size() );
        }
    }

    public void saveData() throws IOException {
        // Speichern
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            // Die Fahrzeug-Liste "serialisieren", d.h. alle Objekte mit ihren Attributen
            // in den Stream speichern (Das Ergebnis ist ein Binär-File)
            oos.writeObject(fahrzeuge);
            // den Zähler für die Fahrzeugnummer auch speichern
            oos.writeInt(Fahrzeug.getNextNr());
        }
    }
}
